package com.bns.herramientacovid19.database;

public class Conteo {

    public String nombre;
    public int casos;

    public Conteo(String nombre, int casos) {
        this.nombre = nombre;
        this.casos = casos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        nombre = nombre;
    }

    public int getCasos() {
        return casos;
    }

    public void setCasos(int casos) {
        this.casos = casos;
    }
}
