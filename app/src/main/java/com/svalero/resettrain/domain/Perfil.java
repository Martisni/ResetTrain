package com.svalero.resettrain.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Perfil {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String ritmo;
    @ColumnInfo
    private String peso;
    @ColumnInfo
    private String medidas;
    @ColumnInfo
    private String fecha;
    @ColumnInfo
    private boolean obesidad;

    public Perfil() {
    }

    public Perfil(String ritmo, String peso, String medidas, String fecha) {
        this.ritmo = ritmo;
        this.peso = peso;
        this.medidas = medidas;
        this.fecha = fecha;
        this.obesidad = false;
    }

    public String getRitmo() {
        return ritmo;
    }

    public void setRitmo(String ritmo) {
        this.ritmo = ritmo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isObesidad() {
        return obesidad;
    }

    public void setObesidad(boolean obesidad) {
        this.obesidad = obesidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
