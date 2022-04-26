package com.example.playasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.playasapp.adapters.AdapterPages;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 *
 * Class MainActivity tiene los adapatadores de Playas y Piscinas y se accede desde la pantalla principal
 *
 * Tambien da soporte a la clase Ajustes siendo esta el menu.
 *
 *
 *
 */

public class MainActivity extends AppCompatActivity {

    //FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    //FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 view2 = findViewById(R.id.viewPage);
        view2.setAdapter(new AdapterPages(this));

        final TabLayout tabLayout = findViewById(R.id.tabla);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, view2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Playas");
                    tab.setIcon(R.drawable.ic_beach);
                    break;
                case 1:
                    tab.setText("Piscinas");
                    tab.setIcon(R.drawable.ic_swimming_pool);
                    break;
                case 2:
                    tab.setText("Todo");
                    tab.setIcon(R.drawable.ic_swim);
                    break;


            }
        });

        tabLayoutMediator.attach();
        view2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                BadgeDrawable badgeDrawable = tabLayout.getTabAt(position).getOrCreateBadge();
                badgeDrawable.setVisible(false);

            }
        });


    }


    /**
     *
     * Funcion de la creacion del menu lateral de la clase Ajustes
     *
     * Pasamos como param Menu y nos devuelve el boolean true cuando se crea
     *
     * @param menu
     * @return
     */

    public boolean onCreateOptionMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_drawer, menu);
        return true;
    }


    /**
     *
     * Funcion para cerrar sesion desde la barra de arriba de la app.
     *
     *
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.boton_out:
                AuthUI.getInstance().signOut(this).addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                finish();
                                Toast.makeText(MainActivity.this, "Cerrando session", Toast.LENGTH_SHORT).show();
                                login();
                            }
                        }
                );
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void login(){
        Intent i = new Intent(this, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}