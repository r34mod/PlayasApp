package com.example.playasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.widget.Toolbar;
//import com.bumptech.glide.Glide;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 *
 * Clase Ajustes tiene el menu lateral del principio de la app
 *
 * En esta clase nos permite navegar a todas las otras clases creadas, como Perfil, Mapa, Playas...
 *
 *
 */
public class Ajustes extends AppCompatActivity {

    FirebaseAuth mAuth;

    FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    private AppBarConfiguration mAppBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow).setDrawerLayout(drawer).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.getMenu().findItem(R.id.nav_home).setOnMenuItemClickListener(
                (menuItem)->{
                    Intent i = new Intent(Ajustes.this, Ajustes.class);
                    startActivity(i);
                    return false;
                }
        );

        navigationView.getMenu().findItem(R.id.nav_sesion).setOnMenuItemClickListener(
                (menuItem)->{
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(Ajustes.this, "Sesion cerrada", Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(Ajustes.this, Login.class);
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

        navigationView.getMenu().findItem(R.id.nav_loca).setOnMenuItemClickListener((menuItem)->{
            Intent a = new Intent(Ajustes.this, LocationPlayas.class);
            startActivity(a);
            return false;
        });

        navigationView.getMenu().findItem(R.id.nav_map).setOnMenuItemClickListener(
                (MenuItem)->{
                    Intent a = new Intent(Ajustes.this, MapsActivity.class);
                    startActivity(a);
                    return false;
                }
        );

        navigationView.getMenu().findItem(R.id.nav_profile).setOnMenuItemClickListener(
                (menuItem)->{
                    Intent b = new Intent(Ajustes.this, PerfilUser.class);
                    startActivity(b);
                    return false;
                }
        );

        navigationView.getMenu().findItem(R.id.nav_info).setOnMenuItemClickListener(
                (menuItem)->{
                    Intent b = new Intent(Ajustes.this, InfoActivity.class);
                    startActivity(b);
                    return false;
                }
        );


        nuevaNavegacion();


    }

    /**
        Funcion para la creacion del menu lateral. Llamada al XML donde esta maquetado
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    /**
     *
     * Funcion de control de cierre de sesion
     *
     * @param item
     * @return boolean para saber si cierra la sesion el usuario
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        boolean cerrado = false;

        switch (item.getItemId()){
            case R.id.nav_sesion:
                mAuth.signOut();
                cerrado = true;
                finish();
                Toast.makeText(Ajustes.this, "Sesion cerrada", Toast.LENGTH_SHORT).show();
                sessionCerrada();
            break;
        }

        return cerrado;
    }

    /**
     * Funcion que cierra la sesion, limpia cache y te saca de la app
     *
     */

    private void sessionCerrada(){
        Intent i = new Intent(this, Register.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    /**
     *
     * Funcion de controlador del mapeado de Navegacion para moverte a distintas pantallas
     * @return
     */

    @Override
    public boolean onSupportNavigateUp(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    /**
     *
     * Funcion de controlador del mapeado de Navegacion para moverte a distintas pantallas y ejecucion del mapeado
     *
     */

    public void nuevaNavegacion(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        TextView username = header.findViewById(R.id.nav_user);
        TextView email = header.findViewById(R.id.nav_mail);
        ImageView userphoto = header.findViewById(R.id.imgUser);

        //fireuser
        username.setText(fireUser.getDisplayName());
        email.setText(fireUser.getEmail());
        //Glide.with(this).load(fireUser.getPhotoUrl()).into(userphoto);
        //Glide.with(this).load("jj");
    }



}