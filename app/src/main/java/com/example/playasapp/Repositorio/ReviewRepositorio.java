package com.example.playasapp.Repositorio;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.playasapp.PlayasDB.Beachs;
import com.example.playasapp.PlayasDB.PlayaDao;
import com.example.playasapp.PlayasDB.PlayasDB;
import com.example.playasapp.PlayasDB.ReviewDao;
import com.example.playasapp.PlayasDB.ReviewModel;

import java.util.List;

public class ReviewRepositorio {

    private PlayasDB beachdb;

    public ReviewRepositorio(Application application) {
        beachdb = PlayasDB.getINSTANCE(application);
    }

    public LiveData<List<ReviewModel>> getAllModel(String beachsId) {
        return beachdb.reviewDao().getAllModel(beachsId);
    }

    public void insert(ReviewModel reviewModel) {
        new InsertAsynTask(beachdb).execute(reviewModel);
    }


    static class UpdateAsynTask extends AsyncTask<Beachs, Void, Void>{
        private PlayaDao reviewDao;

        UpdateAsynTask(PlayasDB playaDao){
            reviewDao = playaDao.playaDao();
        }

        @Override
        protected Void doInBackground(Beachs... beachs) {
            Beachs beachs1 = beachs[0];
            reviewDao.update(!beachs1.thumb, beachs1.beachsId);
            return null;
        }
    }

    // to insert data in background
    static class InsertAsynTask extends AsyncTask<ReviewModel, Void, Void> {

        private ReviewDao reviewDao;

        InsertAsynTask(PlayasDB beachdb) {
            reviewDao = beachdb.reviewDao();
        }


        @Override
        protected Void doInBackground(ReviewModel... lists) {
            reviewDao.insert(lists[0]);
            return null;
        }
    }
}
