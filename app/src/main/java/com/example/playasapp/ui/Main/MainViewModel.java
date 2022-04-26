package com.example.playasapp.ui.Main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 *
 * Clase MainViewModel
 *
 */


public class MainViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public MainViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText(){
        return mText;
    }
}
