package com.example.playasapp.PlayasDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "review")
public class ReviewModel {
    @PrimaryKey(autoGenerate = true)
    public int reviewId;

    public String beachsId;

    public String comment;
}
