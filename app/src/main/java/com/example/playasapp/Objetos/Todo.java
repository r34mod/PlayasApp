package com.example.playasapp.Objetos;

/**
 *
 * Clase Objeto Todos
 *
 */


public class Todo {

    private String NombreTodo;
    private String descriptTodo;
    private String ubicacionTodo;
    private String puntosTodo;
    private int puntuacionTodo;
    private String imgTodo;
    private String id;

    public Todo(String nombreTodo, String descriptTodo, String ubicacionTodo, String puntosTodo, String imgTodo, int puntuacionTodo, String id) {
        NombreTodo = nombreTodo;
        this.descriptTodo = descriptTodo;
        this.ubicacionTodo = ubicacionTodo;
        this.puntosTodo = puntosTodo;
        this.puntuacionTodo = puntuacionTodo;
        this.imgTodo = imgTodo;
        this.id = id;
    }

    public Todo() {
    }


    public String getNombreTodo() {
        return NombreTodo;
    }

    public void setNombreTodo(String nombreTodo) {
        NombreTodo = nombreTodo;
    }

    public String getDescriptTodo() {
        return descriptTodo;
    }

    public void setDescriptTodo(String descriptTodo) {
        this.descriptTodo = descriptTodo;
    }

    public String getUbicacionTodo() {
        return ubicacionTodo;
    }

    public void setUbicacionTodo(String ubicacionTodo) {
        this.ubicacionTodo = ubicacionTodo;
    }

    public String getPuntosTodo() {
        return puntosTodo;
    }

    public void setPuntosTodo(String puntosTodo) {
        this.puntosTodo = puntosTodo;
    }

    public int getPuntuacionTodo() {
        return puntuacionTodo;
    }

    public void setPuntuacionTodo(int puntuacionTodo) {
        this.puntuacionTodo = puntuacionTodo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgTodo() {
        return imgTodo;
    }

    public void setImgTodo(String imgTodo) {
        this.imgTodo = imgTodo;
    }
}
