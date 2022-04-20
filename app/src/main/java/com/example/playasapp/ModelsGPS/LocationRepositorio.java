package com.example.playasapp.ModelsGPS;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.example.playasapp.Peds.Playa;
import com.example.playasapp.PlayasDB.PlayasDB;

import java.util.List;

public class LocationRepositorio {
    //Clase para almacenar los datos obtenidos al momento
    private PlayasDB db;

    public LocationRepositorio(Application application){
        db = PlayasDB.getINSTANCE(application);
    }

    public void insert(List<Playa> modelList){
        new InsertAsynTask(db).execute(modelList);
    }

    public LiveData<List<Playa>> getAllModel(String latlonString){
        return db.playaDao().getAllModel(latlonString);
    }

    public void update(Playa playaModel){
        new UpdateAsynTask(db).execute(playaModel);
    }

    static class UpdateAsyncTask extends AsyncTask<Playa, Void, Void>{
        private PlayaDao playaDao;

        UpdateAsyncTask(PlayasDB db){
            playaDao = db.playaDao();
        }

        @Override
        protected Void doInBackground(Playa... playa){
            Playa playa1 = playa[0];
            playaDao.update(!playa1.thumb, playa1.playaId);
            return null;
        }
    }


    static class InsertAsynTask extends AsyncTask<List<Playa>, Void, Void>{
        private PlayaDao playaDao;

        InsertAsynTask(PlayasDB db){
            playaDao = db.playaDao();
        }

        @Override
        protected Void doInBackground(List<Playa>... playas){

            playaDao.insert(playas[0]);
            return null;
        }
    }

}
