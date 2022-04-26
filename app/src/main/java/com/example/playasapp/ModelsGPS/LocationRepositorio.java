package com.example.playasapp.ModelsGPS;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.playasapp.PlayasDB.Beachs;
import com.example.playasapp.PlayasDB.PlayasDB;
import com.example.playasapp.PlayasDB.PlayaDao;

import java.util.List;

/**
 * @Class LocationRepositorio
 *
 *
 *
 *
 */
public class LocationRepositorio {
    //Clase para almacenar los datos obtenidos al momento
    private PlayasDB db;

    public LocationRepositorio(Application application){
        db = PlayasDB.getINSTANCE(application);
    }

    public void insert(List<Beachs> modelList){
        new InsertAsynTask(db).execute(modelList);
    }

    public LiveData<List<Beachs>> getAllModel(String latlonString){
        return db.playaDao().getAllModel(latlonString);
    }

    public void update(Beachs playaModel){
        new UpdateAsynTask(db).execute(playaModel);
    }

    static class UpdateAsyncTask extends AsyncTask<Beachs, Void, Void>{
        private PlayaDao playaDao;

        UpdateAsyncTask(PlayasDB db){
            playaDao = db.playaDao();
        }

        @Override
        protected Void doInBackground(Beachs... beach){
            Beachs beach1 = beach[0];
            playaDao.update(!beach1.thumb, beach1.beachsId);
            return null;
        }
    }


    /**
     *
     * Funcion para insertar los datos de playa en la bbdd interna DAO
     *
     * Estos datos a insertar se obtienen de la API
     *
     * Coge los datos de NOMBRE, LATLON
     *
     * Beach{
     *     "beachsId=" + beachsId +
     *     ", name=" + name + '\'' +
     *     ", latlon=" + latlon + '\'' +
     *     ", address=" + address + '\'' + '}'
     *
     * }
     *
     */

    static class InsertAsynTask extends AsyncTask<List<Beachs>, Void, Void>{
        private PlayaDao playaDao;

        InsertAsynTask(PlayasDB db){
            playaDao = db.playaDao();
        }

        @Override
        protected Void doInBackground(List<Beachs>... beachs){

            playaDao.insert(beachs[0]);
            return null;
        }
    }

    /**
     *
     * Funcion para actualizar los datos de alguna playa que ya existiera o cambiar
     * la lista de recomendaciones anteriorrmente obtenida
     *
     */

    static class UpdateAsynTask extends AsyncTask<Beachs, Void, Void>{
        private PlayaDao playaDao;

        public UpdateAsynTask(PlayasDB db) {
            playaDao = db.playaDao();
        }

        @Override
        protected Void doInBackground(Beachs... beachs){
            Beachs beach1 = beachs[0];
            playaDao.update(!beach1.thumb, beach1.beachsId);
            return null;
        }
    }

}
