package com.example.playasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Ajustes extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow).setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.getMenu().findItem(R.id.nav_home).setOnMenuItemClickListener(
                (menuItem)->{
                    Intent i = new Intent(Ajustes.this, MainActivity.class);
                    startActivity(i);
                    return false;
                }
        );

        navigationView.getMenu().findItem(R.id.nav_sesion).setOnMenuItemClickListener(
                (menuItem)->{
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(Ajustes.this, "Sesion cerrada", Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(Ajustes.this, MainActivity.class);
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(a);

                    return false;
        });

        navigationView.getMenu().findItem(R.id.nav_fav).setOnMenuItemClickListener(
                (menuItem)-> {
                    Intent a = new Intent(Ajustes.this, MainActivity.class);
                    startActivity(a);
                    return false;
                });

        navigationView.getMenu().findItem(R.id.nav_perfil).setOnMenuItemClickListener(
                (menuItem)->{
                    Intent b = new Intent(Ajustes.this, MainActivity.class);
                    startActivity(b);
                    return false;
                }
        );

    }



}