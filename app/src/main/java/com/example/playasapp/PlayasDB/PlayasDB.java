package com.example.playasapp.PlayasDB;


import android.content.Context;
import android.telecom.Call;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.playasapp.ModelsGPS.PlayaDao;
import com.example.playasapp.Peds.Playa;

import javax.security.auth.callback.Callback;

@Database(entities = {Playa.class}, version=2, exportSchema = false)
public abstract class PlayasDB extends RoomDatabase {
    private static final String DATABASE_NAME = "PlayaDB";

    public abstract PlayaDao playaDao();

    public abstract PlayasDB playaDB();

    private static volatile PlayasDB INSTANCE;

    public static PlayasDB getINSTANCE(Context context){
        if(INSTANCE == null){
            synchronized (PlayasDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context, PlayasDB.class, DATABASE_NAME)
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
        }
    };
}
