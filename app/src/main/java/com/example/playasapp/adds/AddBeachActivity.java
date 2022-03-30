package com.example.playasapp.adds;

import android.content.Intent;
import android.os.Bundle;

import com.example.playasapp.adapters.AdapPlayas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.playasapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddBeachActivity extends AppCompatActivity {

    FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Playa");

    TextView nombre, descripcion, ubicacion;
    ImageButton img_salir;
    ImageView imgPlaya;
    Button btnAddBeach, btnDeletBeach;
    EditText nombreBeach, descripcionBeach, ubicacionBeach;

    String id = fireUser.getUid();

    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beach);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = findViewById(R.id.fab);
       // fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
         //   public void onClick(View view) {
          //      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
       // });


        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(AddBeachActivity.this, "La puntacion dada es: "+rating, Toast.LENGTH_SHORT).show();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Users");

        nombre = findViewById(R.id.nameTxt);
        nombreBeach = findViewById(R.id.nameBeach);

        descripcion = findViewById(R.id.descriptTxt);
        descripcionBeach = findViewById(R.id.descriptBeach);

        ubicacion = findViewById(R.id.ubicationTxt);
        ubicacionBeach = findViewById(R.id.ubicationBeach);

        img_salir = findViewById(R.id.btn_salir);
        btnAddBeach = findViewById(R.id.addBeach);

        btnDeletBeach = findViewById(R.id.deleteBeach);

        imgPlaya = findViewById(R.id.photoBeach);


        img_salir.setOnClickListener(v -> {
            Intent i = new Intent(AddBeachActivity.this, AdapPlayas.class);
            startActivity(i);
        });

        btnAddBeach.setOnClickListener(v -> {
            actualizarPlaya(nombreBeach, ubicacionBeach, descripcionBeach, ratingBar);
        });

        btnDeletBeach.setOnClickListener(v ->{
            deleteBeach(id);
            Intent i = new Intent(AddBeachActivity.this, AdapPlayas.class);
            startActivity(i);
        });
    }

    private void deleteBeach(String id){
        reference = FirebaseDatabase.getInstance().getReference("Playa").child(id);
        reference.removeValue();
        Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
    }


    private void actualizarPlaya(EditText nombreBeach, EditText ubicacionBeach, EditText descripcionBeach, RatingBar ratingBar){
        String namePlaya = nombreBeach.getText().toString();
        String ubicPlaya = ubicacionBeach.getText().toString();
        String descripPlaya = descripcionBeach.getText().toString();
        float puntuacion = ratingBar.getRating();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference("Playa");
        reference.child(fireUser.getUid()).child("namePlaya").setValue(namePlaya);
        reference.child(fireUser.getUid()).child("ubicPlaya").setValue(ubicPlaya);
        reference.child(fireUser.getUid()).child("descripPlaya").setValue(descripPlaya);
        reference.child(fireUser.getUid()).child("puntuacionPlaya").setValue(puntuacion);
    }
}