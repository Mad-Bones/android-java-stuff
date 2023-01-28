package com.bns.herramientacovid19.pantallas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Transaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bns.herramientacovid19.R;
import com.bns.herramientacovid19.database.BaseDatos;
import com.bns.herramientacovid19.database.Objeto;
import com.bns.herramientacovid19.database.ObjetosDAO;
import com.bns.herramientacovid19.fragmento.FragmentoInicial;
import com.bns.herramientacovid19.fragmento.FragmentoLista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Objeto> listafull;
    boolean lista, inicio;
    RelativeLayout dialogoProgreso;
    TextView botnStop;
    BufferedReader in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicial);
        lista = false;
        inicio = false;
        listafull = new ArrayList<>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        iniciarUI();
        if(inicio){
            cambiarInicio();
        }else if(lista){
            cambiarLista();
        }else{
            cambiarInicio();
        }
    }

    private void iniciarUI(){
        dialogoProgreso = findViewById(R.id.dialogo_progreso);
        botnStop = findViewById(R.id.boton_parar);
    }
    public ArrayList<Objeto> getListafull() {
        return listafull;
    }
    public void setListafull(ArrayList<Objeto> listafull) {
        this.listafull = listafull;
    }
    public void cambiarInicio() {
        inicio = true;
        lista = false;
        FragmentoInicial fragdatos = new FragmentoInicial();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_frags, fragdatos, "INICIO")
                .commit();
    }
    public void cambiarLista() {
        inicio = false;
        lista = true;
        FragmentoLista fragdatos = new FragmentoLista();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedor_frags, fragdatos, "LISTA")
                .commit();
    }

    public void obtenerDatos(){
        dialogoProgreso.setVisibility(View.VISIBLE);
        try {
            URL url = new URL("https://files.minsa.gob.pe/s/eRqxR35ZCxrzNgr/download/positivos_covid.csv");
            //URL url = new URL("https://www.appsloveworld.com/wp-content/uploads/2018/10/Sample100.csv");


            in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = "";

            botnStop.setOnClickListener(v->{
                try {
                    in.close();
                    dialogoProgreso.setVisibility(View.GONE);
                } catch (IOException e) {
                    e.printStackTrace();
                    dialogoProgreso.setVisibility(View.GONE);
                }
            });
            try {
                in.readLine();
                while ((line = in.readLine()) != null) {

                    Log.d("ActividadInicial", "Line: " + line);

                    String[] tokens = line.split(";");

                    final Objeto objeto = new Objeto();
                    objeto.setFecha_corte(tokens[0]);
                    objeto.setDepartamento(tokens[1]);
                    objeto.setProvincia(tokens[2]);
                    objeto.setDistrito(tokens[3]);
                    objeto.setEdad(tokens[5]);
                    objeto.setSexo(tokens[6]);
                    objeto.setFecha_resultado(tokens[7]);
                    objeto.setUbigeo(tokens[8]);
                    objeto.setId_persona(tokens[9]);


                    guardarDatos(objeto);
                }
            } catch (IOException e) {

                Log.wtf("ActividadInicial", "ERROR AL LEER DATOS EN LA LINEA" + line, e);
                e.printStackTrace();
            }
            in.close();
            dialogoProgreso.setVisibility(View.GONE);
            cambiarInicio();

        } catch (IOException e) {
            Log.wtf("ActividadInicial", "ERROR" + e.getMessage());
            dialogoProgreso.setVisibility(View.GONE);
        }

    }

    public void guardarDatos(Objeto objeto){
        @SuppressLint("StaticFieldLeak")
        class SaveDataTask extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                BaseDatos.getDatabase(getApplicationContext()).objetosDao().insertObjeto(objeto);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }

        new SaveDataTask().execute();
    }

    public void borrarDatos() {
        BaseDatos databae = BaseDatos.getDatabase(MainActivity.this);
        ObjetosDAO dao = databae.objetosDao();
        @SuppressLint("StaticFieldLeak")
        class DeleteAllData extends AsyncTask<Void, Void, Void> {
            private ObjetosDAO dao;
            private DeleteAllData(ObjetosDAO dao) {
                this.dao = dao;
            }
            @Override
            protected Void doInBackground(Void... voids) {
                dao.delete();
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
                cambiarInicio();
                Toast.makeText(MainActivity.this, "Base de datos eliminada", Toast.LENGTH_SHORT).show();
            }
        }
        new DeleteAllData(dao).execute();
    }

    @Override
    public void onBackPressed() {
        if(lista){
            cambiarInicio();
        }else if(inicio){
            super.onBackPressed();
        }
    }



}