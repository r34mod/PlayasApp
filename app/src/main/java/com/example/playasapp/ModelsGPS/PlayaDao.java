package com.example.playasapp.ModelsGPS;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.playasapp.Peds.Playa;

import java.util.List;

@Dao
public interface PlayaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Playa> modelList);

    @Query("SELECT * from playa WHERE latlong=:latlong")
    LiveData<List<Playa>> getAllModel(String latlong);

    @Query("DELETE from playa")
    void deleteAll();

    @Query("UPDATE playa SET thumb=:thumb WHERE playasId = :playaId")
    void update(boolean thumb, String playasId);


}
