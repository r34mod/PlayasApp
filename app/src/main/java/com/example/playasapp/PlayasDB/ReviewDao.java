package com.example.playasapp.PlayasDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReviewDao {
    @Insert
    void insert(ReviewModel review);
    @Query("SELECT * from review WHERE beachsId = :beachsId")
    LiveData<List<ReviewModel>> getAllModel(String beachsId);
}
