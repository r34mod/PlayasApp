package com.example.playasapp.ui.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.*;

import com.example.playasapp.R;

public class MainFragment extends Fragment {

    private MainFragment mainFragment;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanveState){
        mainFragment = ViewModelProviders.of(this).get(MainFragment.class);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.txtMain);

        return root;
    }

}
