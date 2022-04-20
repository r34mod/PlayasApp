package com.example.playasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    ImageButton exit, gps;


    private GoogleMap mMap;

    ArrayList<LatLng> arrayList = new ArrayList<LatLng>();
    LatLng playa1 = new LatLng(41.18839097427671, 1.5923368590954228);
    LatLng playa2 = new LatLng(43.4130597405055, -2.94441553456762);
    LatLng playa3 = new LatLng(36.495683028953806, -6.274662472157798);
    LatLng piscina1 = new LatLng(40.41854069795483, -3.805338888319056);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        arrayList.add(playa1);
        arrayList.add(playa2);
        arrayList.add(playa3);
        arrayList.add(piscina1);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main();
            }
        });

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MapsActivity.this, "Localizando", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void main() {
        Intent a = new Intent(this, MainActivity.class);
        startActivity(a);
    }

    @Override
    public void onMapReady (GoogleMap googleMap){
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(playa1).title("Playa Mediterraneo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(playa1));

        mMap.addMarker(new MarkerOptions().position(playa2).title("Playa Cantabrico"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(playa2));

        mMap.addMarker(new MarkerOptions().position(playa3).title("Playa Atlantico"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(playa3));

        mMap.addMarker(new MarkerOptions().position(piscina1).title("Piscina"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(piscina1));
    }
}