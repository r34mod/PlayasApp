package com.example.playasapp.ModelsGPS;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.playasapp.PlayasDB.Beachs;
import com.example.playasapp.PlayasDB.ReviewModel;
import com.example.playasapp.Repositorio.ReviewRepositorio;

import java.util.List;

public class CommentsViewModel extends AndroidViewModel {
    private final ReviewRepositorio reviewRepository;
    String beachsId;
    LiveData<List<ReviewModel>> playaOnLatlon;

    public CommentsViewModel(@NonNull Application application, String beachsId) {
        super(application);
        this.beachsId = beachsId;
        reviewRepository = new ReviewRepositorio(application);
        playaOnLatlon = reviewRepository.getAllModel(beachsId);
    }

    public void insert(String review) {
        ReviewModel reviewModel = new ReviewModel();
        reviewModel.comment = review;
        reviewModel.beachsId = beachsId;
        reviewRepository.insert(reviewModel);
    }

    public LiveData<List<ReviewModel>> getComments() {
        return playaOnLatlon;
    }
}
