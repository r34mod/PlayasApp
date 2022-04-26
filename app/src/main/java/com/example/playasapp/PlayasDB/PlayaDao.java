package com.example.playasapp.PlayasDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 *
 * Interfaz de PlayaDao (Base de datos interna de la app)
 *
 * Utilizando la biblioteca Room
 *
 * Se utiliza usando objetos de acceso a datos
 *
 * Utilizamos en este caso PLayaDao para la obtencion de un listado en tiempo real
 * de playas cercanas a nuestra localizacion
 *
 * All implement via API
 *
 */


@Dao
public interface PlayaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Beachs> modelList);

    @Query("SELECT * from beachs WHERE latlon=:latlong")
    LiveData<List<Beachs>> getAllModel(String latlong);

    @Query("DELETE from beachs")
    void deleteAll();

    @Query("UPDATE beachs SET thumb=:thumb WHERE beachsId = :beachsId")
    void update(boolean thumb, String beachsId);


}
