package com.example.playasapp.Repositorio;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.playasapp.PlayasDB.Beachs;
import com.example.playasapp.PlayasDB.PlayaDao;
import com.example.playasapp.PlayasDB.PlayasDB;

import java.util.List;

public class BeachRepositorio {
    private PlayasDB db;

    public BeachRepositorio(Application application){
        db = PlayasDB.getINSTANCE(application);
    }

    public void insert(List<Beachs> modelList){
        new BeachRepositorio.InsertAsynTask(db).execute(modelList);
    }

    public LiveData<List<Beachs>> getAllModel(String latlon){
        return db.playaDao().getAllModel(latlon);
    }

    public void update(Beachs beachsModel){
        new UpdateAsynTask(db).execute(beachsModel);
    }

    static class UpdateAsynTask extends AsyncTask<Beachs, Void, Void>{
        private PlayaDao playaDao;

        UpdateAsynTask(PlayasDB playasDB){
            playaDao = playasDB.playaDao();
        }

        @Override
        protected Void doInBackground(Beachs... beachs) {
            Beachs beachs1 = beachs[0];
            playaDao.update(!beachs1.thumb, beachs1.beachsId);
            return null;
        }
    }

    static class InsertAsynTask extends AsyncTask<List<Beachs>, Void, Void>{
        private PlayaDao playaDao;

        InsertAsynTask(PlayasDB playasDB){
            playaDao = playasDB.playaDao();
        }

        @Override
        protected Void doInBackground(List<Beachs>... lists) {
            playaDao.insert(lists[0]);
            return null;
        }
    }
}
