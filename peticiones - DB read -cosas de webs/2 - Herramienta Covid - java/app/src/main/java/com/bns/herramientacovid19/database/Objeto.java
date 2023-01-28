package com.bns.herramientacovid19.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "objeto")
public class Objeto implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "fecha_corte")
    private String fecha_corte;
    @ColumnInfo(name = "departamento")
    private String departamento;
    @ColumnInfo(name = "provincia")
    private String provincia;
    @ColumnInfo(name = "distrito")
    private String distrito;
    @ColumnInfo(name = "metododx")
    private String metododx;
    @ColumnInfo(name = "edad")
    private String edad;
    @ColumnInfo(name = "sexo")
    private String sexo;
    @ColumnInfo(name = "fecha_resultado")
    private String fecha_resultado;
    @ColumnInfo(name = "ubigeo")
    private String ubigeo;
    @ColumnInfo(name = "id_persona")
    private String id_persona;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha_corte() {
        return fecha_corte;
    }

    public void setFecha_corte(String fecha_corte) {
        this.fecha_corte = fecha_corte;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getMetododx() {
        return metododx;
    }

    public void setMetododx(String metododx) {
        this.metododx = metododx;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_resultado() {
        return fecha_resultado;
    }

    public void setFecha_resultado(String fecha_resultado) {
        this.fecha_resultado = fecha_resultado;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    @NonNull
    @Override
    public String toString() {
        return provincia;
    }
}
