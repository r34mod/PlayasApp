package com.example.playasapp.PlayasDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


/**
 * Clase Beachs con atributos para la bbdd de la API y sacar sus atributos de internet
 *
 *
 *
 */
@Entity(tableName = "beachs", indices = @Index(value = {"beachsId"}, unique=true))
public class Beachs {

    @PrimaryKey
    @NonNull
    public String beachsId;

    @SerializedName("name")
    @ColumnInfo(name="name")
    public String name;

    @SerializedName("latlon")
    @ColumnInfo(name="latlon")
    public String latlon;

    @ColumnInfo(name="thumb")
    public boolean thumb;

    public String address;

    @Override
    public String toString(){
        return "Model{ "+
                "beachsId=" + beachsId +
                ", name=" + name + '\'' +
                ", latlon=" + latlon + '\'' +
                ", address=" + address + '\'' + '}'
                ;
    }


}
