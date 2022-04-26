package com.example.playasapp.ModelsGPS;

import java.util.ArrayList;

/**
 *
 * Clase objeto Recommendation
 *
 * Se ocupa en sacar el listado de los sitios recomendados por su nombre
 * Ese nombre se asocia al objeto ITEM y se obtiene un arrayList de ITEMS
 *
 * ITEM -> VENUE{
 *
 *      public String name;
 *      public String id;
 *      public Location location;
 *      public ArrayList<Categories> categories;
 * };
 *
 */

public class Recommendation {

    public String name;
    public ArrayList<Item> items;

}
