package com.example.playasapp.Objetos;


/**
 *
 * Clase Objeto Piscina
 *
 *
 *
 */

public class Piscina {
    private String id;
    private String nombrePiscina;
    private String ubicacionPiscina;
    private String descripcionPiscina;
    private String imgPiscina;
    private int puntuacionPiscina;

    public Piscina(){

    }


    public Piscina(String id, String nombrePiscina, String ubicacionPiscina, String descripcionPiscina, String imgPiscina, int puntuacionPiscina) {
        this.id = id;
        this.nombrePiscina = nombrePiscina;
        this.ubicacionPiscina = ubicacionPiscina;
        this.descripcionPiscina = descripcionPiscina;
        this.imgPiscina = imgPiscina;
        this.puntuacionPiscina = puntuacionPiscina;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombrePiscina() {
        return nombrePiscina;
    }

    public void setNombrePiscina(String nombrePiscina) {
        this.nombrePiscina = nombrePiscina;
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
