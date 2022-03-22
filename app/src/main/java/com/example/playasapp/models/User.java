package com.example.playasapp.models;

import androidx.annotation.NonNull;

import com.google.firebase.encoders.annotations.Encodable;

public class User {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "email")
    private String email;

    public User(){

    }


    public User(String name, String email){
        this.name = name;
        this.email = email;
    }


    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
