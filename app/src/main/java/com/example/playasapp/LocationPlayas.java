package com.example.playasapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playasapp.ModelsGPS.ModelsLocation;
import com.example.playasapp.Objetos.Playa;
import com.example.playasapp.PlayasDB.Beachs;
import com.example.playasapp.adapters.AdapBeach;
import com.example.playasapp.adapters.AdapPlayas;
import com.example.playasapp.fragmentos.commentsFragment;

import java.util.ArrayList;
import java.util.List;

public class LocationPlayas extends AppCompatActivity implements AdapBeach.ThumbListener {

    ImageButton button, buttonSalir;
    //RecyclerView
    RecyclerView recyclerView;
    public ModelsLocation modelsLocation;
    public AdapBeach adapBeach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        button = findViewById(R.id.gps);
        buttonSalir = findViewById(R.id.salir);
        //recyclerview
        recyclerView = findViewById(R.id.listaPlayas);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        List<Beachs> modelList = new ArrayList<>();
        adapBeach = new AdapBeach(this, (ArrayList<Beachs>) modelList, this);
        modelsLocation = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ModelsLocation.class);
        modelsLocation.getPlayasLocation().observe(this, new Observer<List<Beachs>>() {
            @Override
            public void onChanged(List<Beachs> beachs) {
                adapBeach.getAllModels(beachs);
                recyclerView.setAdapter(adapBeach);
            }
        });

        //Permisos
        if (ContextCompat.checkSelfPermission(LocationPlayas.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LocationPlayas.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        } else {
            modelsLocation.getLocation();
        }


        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main();
            }
        });


    }



    @Override
    public void onThumb(Beachs playa) {
        modelsLocation.update(playa);
    }

    @Override
    public void onComments(Beachs playa) {
        commentsFragment addPhotoBottomDialogFragment =
                commentsFragment.newInstance(playa.beachsId);
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                "comment");
    }



    public void main() {
        Intent a = new Intent(this, Ajustes.class);
        startActivity(a);
    }


}
