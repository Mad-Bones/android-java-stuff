package com.bns.herramientacovid19.fragmento;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bns.herramientacovid19.R;
import com.bns.herramientacovid19.adaptadores.ObjetosAdapter;
import com.bns.herramientacovid19.database.Conteo;
import com.bns.herramientacovid19.database.Objeto;
import com.bns.herramientacovid19.pantallas.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class FragmentoLista extends Fragment {
    View view;
    private Context context;
    TextView fecha,textoAviso;
    final Calendar myCalendar= Calendar.getInstance();

    ObjetosAdapter objetosAdapter;
    public StaggeredGridLayoutManager objetManager;
    RecyclerView reciclerView;
    private ArrayList<Objeto> listaTempA;
    private ArrayList<Objeto> listaTempB;
    private ArrayList<Conteo> conteoList;

    public FragmentoLista () {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_lista, container, false);
        context = getActivity();
        listaTempA = new ArrayList<>();
        listaTempB = new ArrayList<>();
        conteoList = new ArrayList<>();
        iniciarUi(view);


        if(isAdded()){
            listaTempA = ((MainActivity)requireActivity()).listafull;
            if(listaTempA.isEmpty()){
               textoAviso.setText("No hay data");
            }else{
                if(fecha.getText().toString().trim().contains("Elije")){
                    textoAviso.setText("Primero debes seleccionar una fecha");
                }
            }
        }

        cargarBotones();
        return view;
    }
    private void iniciarUi(View view) {
        reciclerView = view.findViewById(R.id.lista_recicler);
        fecha = view.findViewById(R.id.fecha_imp);
        textoAviso = view.findViewById(R.id.texto_lista);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void cargarBotones(){
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            elejirFechaYFiltrar();
        };
        fecha.setOnClickListener(v->{
            conteoList.clear();
            new DatePickerDialog(context,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        if(isAdded()){
            conteoList.clear();
            filtrarPorProvincias();
            listaTempB.clear();
            objetosAdapter = new ObjetosAdapter(conteoList);
            objetManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            reciclerView.setLayoutManager(objetManager);
            reciclerView.setAdapter(objetosAdapter);
            objetosAdapter.notifyDataSetChanged();
            if(objetosAdapter.getItemCount() > 0){
                textoAviso.setVisibility(View.GONE);
                reciclerView.setVisibility(View.VISIBLE);
            }else if(objetosAdapter.getItemCount() <= 0){
                textoAviso.setVisibility(View.VISIBLE);
                reciclerView.setVisibility(View.GONE);
                textoAviso.setText("No hay data para mostrar");
            }

        }
    }


    private void elejirFechaYFiltrar() {
        String myFormat="dd/MM/yyyy";
        String buscar="yyyy"+"MM"+"dd";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        SimpleDateFormat filtroff=new SimpleDateFormat(buscar, Locale.US);
        fecha.setText(dateFormat.format(myCalendar.getTime()));
        String busqueda = filtroff.format(myCalendar.getTime());


        filtrarPorFecha(busqueda);

    }

    @SuppressLint("NotifyDataSetChanged")
    private void cargarRecicler() {
        if(isAdded()){
            conteoList.clear();
            filtrarPorProvincias();
            listaTempB.clear();
            objetosAdapter = new ObjetosAdapter(conteoList);
            objetManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            reciclerView.setLayoutManager(objetManager);
            reciclerView.setAdapter(objetosAdapter);
            objetosAdapter.notifyDataSetChanged();
            if(objetosAdapter.getItemCount() > 0){
                textoAviso.setVisibility(View.GONE);
                reciclerView.setVisibility(View.VISIBLE);
            }else if(objetosAdapter.getItemCount() <= 0){
                textoAviso.setVisibility(View.VISIBLE);
                reciclerView.setVisibility(View.GONE);
                textoAviso.setText("No hay data para mostrar");
            }

        }
    }
    private void filtrarPorFecha(String busqueda) {
        for (Objeto objeto: listaTempA) {
            if (objeto.getFecha_corte().matches(busqueda)) {
                listaTempB.add(objeto);
                System.out.println(objeto);
            }
        }
        cargarRecicler();
    }
    private void filtrarPorProvincias() {
        ArrayList<String> filteredList = new ArrayList<>();
        for(Objeto objeto : listaTempB){
            if(!objeto.getDepartamento().isEmpty()){
                String texto = objeto.getDepartamento();
                filteredList.add(texto);
            }
        }
        contarCasosProvincias(filteredList);
    }
    public void contarCasosProvincias(ArrayList<String> list) {
        Set<String> st = new HashSet<>(list);
        for (String s : st){
            System.out.println(s + ": " + Collections.frequency(list, s));
            conteoList.add(new Conteo(s,Collections.frequency(list, s)));
        }
        Collections.sort(conteoList, new Comparator<Conteo>() {
            @Override
            public int compare(Conteo lhs, Conteo rhs) {
                return lhs.getNombre().compareTo(rhs.getNombre());
            }
        });
    }



}