package com.bns.herramientacovid19.fragmento;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bns.herramientacovid19.R;
import com.bns.herramientacovid19.database.BaseDatos;
import com.bns.herramientacovid19.database.Objeto;
import com.bns.herramientacovid19.pantallas.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentoInicial extends Fragment {
    View view;
    private Context context;
    Button sincro,limpiar,abrir;
    public ArrayList<Objeto> listafull;
    TextView link;

    public FragmentoInicial() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_inicial, container, false);
        context = getActivity();
        listafull = new ArrayList<>();
        iniciarUi(view);
        cargarBotones();
        getDatabase();
        return view;
    }

    public void getDatabase() {
        listafull.clear();

        new Thread(() -> {
            List<Objeto> notes = BaseDatos.getDatabase(context).objetosDao().fetchAllObjeto();

            listafull.addAll(notes);
            if(listafull.isEmpty()){
                sincro.setBackgroundColor(sincro.getContext().getResources().getColor(R.color.azulneutro));
                sincro.setClickable(true);
            } else {
                sincro.setClickable(false);
                sincro.setBackgroundColor(sincro.getContext().getResources().getColor(R.color.gris));
            }
            if(isAdded()){
                ((MainActivity)requireActivity()).setListafull(listafull);
            }
        }).start();
    }

    private void cargarBotones() {
        if(isAdded()){
            sincro.setOnClickListener(v->{
                ((MainActivity)requireActivity()).obtenerDatos();
            });
            limpiar.setOnClickListener(v->{
                ((MainActivity)requireActivity()).borrarDatos();
            });
            abrir.setOnClickListener(v->{
                ((MainActivity)requireActivity()).cambiarLista();
            });
        }

        link.setOnClickListener(v->{
            verContacto();
        });

    }


    private void iniciarUi(View view) {
        sincro = view.findViewById(R.id.obtener);
        limpiar = view.findViewById(R.id.limpiar);
        abrir = view.findViewById(R.id.preview);
        link = view.findViewById(R.id.link);
    }


    //@TODO ELIMINAR ESTE DIALOGO
    public void verContacto(){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("COOOOONTACTO");
        alertDialog.setMessage("nada por aqui... Diviertete!\n\n" +
                "la gente lee estas cosas?...\n\n" +
                "Creado por Mad Bones! YEAA! ...\n\n");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "CERRAR",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}