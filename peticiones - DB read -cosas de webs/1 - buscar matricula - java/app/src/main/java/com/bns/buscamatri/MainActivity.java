package com.bns.buscamatri;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    EditText numeros;
    TextView botonMatricula;
    AlertDialog dialoloMatricu;

    String datosMatricu;
    HttpURLConnection urlConnection ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarUI();
        iniciarBotones();
    }

    private void iniciarBotones() {
        botonMatricula.setOnClickListener(v -> {
            if(numeros.getText().toString().isEmpty()){
                Toast.makeText(this, "sin datos, no hay resultados...", Toast.LENGTH_SHORT).show();
            }else{
                postDataUsingVolley(numeros.getText().toString());
            }
        });
    }

    private void postDataUsingVolley(String matricula) {  requestQueue = Volley.newRequestQueue(this);
        String URL = "https://****.****.****/****/DatosCuenta?impuesto=AU&cuenta=" + matricula;
         
        JsonArrayRequest arrayReq = new JsonArrayRequest(
                Request.Method.POST,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("CUERPO", String.valueOf(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("VOLLEY", error.toString());
                    }
                }
        );
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("VOLLEY", response);
                mostrarMatricula(response.toString());
            }
        }
        ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
                mostrarMatricula(error.toString());
            }
        });


        requestQueue.add(stringRequest);

    }

    public JSONObject jsonToObject(String t) throws JSONException {
         return new JSONObject(t.substring(t.indexOf("{"),t.lastIndexOf("}") + 1));
    }

    @SuppressLint("JavascriptInterface")
    public void mostrarMatricula(String textoA) {
        if (dialoloMatricu == null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final View view = LayoutInflater.from(this).inflate(R.layout.dialogo_matricula, (ViewGroup) findViewById(R.id.dialogoMatricu));
            builder.setView(view);
            builder.setCancelable(false);
            dialoloMatricu = builder.create();
            dialoloMatricu.setCanceledOnTouchOutside(false);
            dialoloMatricu.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            final WebView pantalla ;
            pantalla = view.findViewById(R.id.mybrowser);
            final MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(this);
            pantalla.addJavascriptInterface(myJavaScriptInterface, "AndroidFunction");
            pantalla.setSoundEffectsEnabled(true);
            pantalla.getSettings().setJavaScriptEnabled(true);
            pantalla.loadData("<html>\n" +
                    "    <head>\n" +
                    "        <meta charset=\"UTF-8\">\n" +
                    "        <meta name=\"viewport\" content=\"width=device-width; user-scalable=0;\" />\n" +
                    "        <title>My HTML</title>\n" +
                    "    </head><body>\n" + textoA + "</body></html>",  "text/html","UTF-8");



            view.findViewById(R.id.textoCancelar).setOnClickListener(v -> {
                dialoloMatricu.dismiss();
            });
        }
        dialoloMatricu.show();
    }


    public class MyJavaScriptInterface {
        Context mContext;

        MyJavaScriptInterface(Context c) {
            mContext = c;
        }

        public void showToast(String toast){
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }

        public void openAndroidDialog(){
            AlertDialog.Builder myDialog = new AlertDialog.Builder(mContext);
            myDialog.setTitle("PELIGRO?");
            myDialog.setMessage("Puedes hacer lo que quieras!");
            myDialog.setPositiveButton("Encendido", null);
            myDialog.show();
        }

    }

    private void iniciarUI(){
         numeros = findViewById(R.id.numero_etc);
        botonMatricula = findViewById(R.id.matricula_search);

    }
}