package com.example.playasapp.ModelsGPS;

import java.util.ArrayList;

/**
 *
 * Clase Objeto Location con los datos a mostrar del punto de interes
 *
 * Atributos como la ciudad, direccion, localizacion exacta
 *
 */

public class Location {

    /*
    Muestra los datos de ciudad, direccion, lat y lon de la playa, piscina o charco
     */
    public String city;
    public String address;
    public double lat;
    public double lon;
    public ArrayList formattedAddress;
}
