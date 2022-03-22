package com.example.playasapp.models;

import androidx.annotation.NonNull;

public class Playa {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "namePlaya")
    private String namePlaya;

    @ColumnInfo(name = "ubicacionPlaya")
    private String ubicacionPlaya;

    @ColumnInfo(name = "descripcionPlaya")
    private String descripcionPlaya;

    @ColumnInfo(name = "imgPlaya")
    private String imgPlaya;

    @ColumnInfo(name = "puntuacionPlaya")
    private int puntuacionPlaya;

    public Playa(){

    }

    public Playa(String namePlaya, String ubicacionPlaya, String descripcionPlaya, String imgPlaya, int puntuacionPlaya){
        this.namePlaya = namePlaya;
        this.ubicacionPlaya = ubicacionPlaya;
        this.descripcionPlaya = descripcionPlaya;
        this.imgPlaya = imgPlaya;
        this.puntuacionPlaya = puntuacionPlaya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamePlaya() {
        return namePlaya;
    }

    public void setNamePlaya(String namePlaya) {
        this.namePlaya = namePlaya;
    }

    public String getUbicacionPlaya() {
        return ubicacionPlaya;
    }

    public void setUbicacionPlaya(String ubicacionPlaya) {
        this.ubicacionPlaya = ubicacionPlaya;
    }

    public String getDescripcionPlaya() {
        return descripcionPlaya;
    }

    public void setDescripcionPlaya(String descripcionPlaya) {
        this.descripcionPlaya = descripcionPlaya;
    }

    public String getImgPlaya() {
        return imgPlaya;
    }

    public void setImgPlaya(String imgPlaya) {
        this.imgPlaya = imgPlaya;
    }

    public int getPuntuacionPlaya() {
        return puntuacionPlaya;
    }

    public void setPuntuacionPlaya(int puntuacionPlaya) {
        this.puntuacionPlaya = puntuacionPlaya;
    }
}
