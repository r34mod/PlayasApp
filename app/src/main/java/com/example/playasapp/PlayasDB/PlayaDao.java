package com.example.playasapp.PlayasDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

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
