package com.example.playasapp.models;

import androidx.annotation.NonNull;

public class Piscina {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "namePiscina")
    private String namePiscina;

    @ColumnInfo(name = "ubicacionPiscina")
    private String ubicacionPiscina;

    @ColumnInfo(name = "descripcionPiscina")
    private String descripcionPiscina;

    @ColumnInfo(name = "imgPiscina")
    private String imgPiscina;

    @ColumnInfo(name = "puntuacionPiscina")
    private int puntuacionPiscina;

    public Piscina(){

    }

    public Piscina(String namePiscina, String ubicacionPiscina, String descripcionPiscina, String imgPiscina, int puntuacionPiscina){
        this.namePiscina = namePiscina;
        this.ubicacionPiscina = ubicacionPiscina;
        this.descripcionPiscina = descripcionPiscina;
        this.imgPiscina = imgPiscina;
        this.puntuacionPiscina = puntuacionPiscina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String namePiscina() {
        return namePiscina;
    }

    public void setNamePiscina(String namePiscina) {
        this.namePiscina = namePiscina;
    }

    public String getUbicacionPiscina() {
        return ubicacionPiscina;
    }

    public void setUbicacionPiscina(String ubicacionPiscina) {
        this.ubicacionPiscina = ubicacionPiscina;
    }

    public String getDescripcionPiscina() {
        return descripcionPiscina;
    }

    public void setDescripcionPiscina(String descripcionPiscina) {
        this.descripcionPiscina = descripcionPiscina;
    }

    public String getImgPiscina() {
        return imgPiscina;
    }

    public void setImgPiscina(String imgPiscina) {
        this.imgPiscina = imgPiscina;
    }

    public int getPuntuacionPiscina() {
        return puntuacionPiscina;
    }

    public void setPuntuacionPiscina(int puntuacionPiscina) {
        this.puntuacionPiscina = puntuacionPiscina;
    }
}
