package com.svalero.resettrain.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Rutina {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String modalidad;
    @ColumnInfo
    private boolean material;
    @ColumnInfo
    private String series;
    @ColumnInfo
    private String repeticiones;
    @ColumnInfo
    private String dias;
    @ColumnInfo
    private String duracion;

    public Rutina() {
    }
    public Rutina(String modalidad, String series, String repeticiones, String dias, String duracion) {
        this.modalidad = modalidad;
        this.series = series;
        this.repeticiones = repeticiones;
        this.dias = dias;
        this.duracion = duracion;
        this.material = false;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public boolean isMaterial() {
        return material;
    }

    public void setMaterial(boolean material) {
        this.material = material;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(String repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
