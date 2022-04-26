package com.example.playasapp.ModelsGPS;

import java.util.ArrayList;

/**
 *
 * Clase objeto Response
 *
 * En esta clase obtenemos la localizacion del usuario
 * Los puntos de coordenadas mas lejos del usuario o puntos limites
 *
 * Y crea un arrayList de los lugares que se encuentren mas cerca del usuario
 * y mejores recoomendados por las Reviews internas de GoogleMaps
 *
 *
 */

public class Response {
    public String headerLocation;
    public Bounds suggestedBounds;
    public ArrayList<Recommendation> groups;
}
