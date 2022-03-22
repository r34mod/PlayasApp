package com.example.playasapp.Peds;

public class Playa {

    private String id;
    private String nombrePlaya;
    private String ubicacionPlaya;
    private String descripcionPlaya;
    private String imgPlaya;
    private int puntuacionPlaya;

    public Playa() {
    }

    public Playa(String id, String nombrePlaya, String ubicacionPlaya, String descripcionPlaya, String imgPlaya, int puntuacionPlaya) {
        this.id = id;
        this.nombrePlaya = nombrePlaya;
        this.ubicacionPlaya = ubicacionPlaya;
        this.descripcionPlaya = descripcionPlaya;
        this.imgPlaya = imgPlaya;
        this.puntuacionPlaya = puntuacionPlaya;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrePlaya() {
        return nombrePlaya;
    }

    public void setNombrePlaya(String nombrePlaya) {
        this.nombrePlaya = nombrePlaya;
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
