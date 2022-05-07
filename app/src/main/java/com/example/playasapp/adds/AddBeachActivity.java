package com.example.playasapp.adds;

import android.content.Intent;
import android.os.Bundle;

import com.example.playasapp.adapters.AdapPlayas;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Clase Para agregar playas a la base de datos de Firebase
 *
 * El usuario registrado podra agregar una playa a la app, agregando informacion de ella
 *
 * Tambien ademas de agregar podra eliminar alguna de las playas creadas por el.
 *
 * NO PUEDE ELIMINAR PLAYAS DE OTROS USUARIOS!
 *
 *
 */



public class AddBeachActivity extends AppCompatActivity {

   private FirebaseFirestore mfirestore;

    TextView nombre, descripcion, ubicacion;
    ImageButton img_salir;
    ImageView imgPlaya;
    Button btnAddBeach, btnDeletBeach;
    EditText nombreBeach, descripcionBeach, ubicacionBeach;



    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beach);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mfirestore = FirebaseFirestore.getInstance();
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
            String id = mfirestore.collection("beach").getId();
            deleteBeach(id);
            Intent i = new Intent(AddBeachActivity.this, AdapPlayas.class);
            startActivity(i);
        });
    }

    /**
     *
     *
     * Funcion para eliminar la playa por Id del usuario
     * @param id
     */
    private void deleteBeach(String id){
        mfirestore.collection("beach").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });

        Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
    }


    /**
     *
     * Agrega una playa a la bbdd, tambien sirve para actualizar la informacion de la misma
     *
     * @param nombreBeach
     * @param ubicacionBeach
     * @param descripcionBeach
     * @param ratingBar
     */
    private void actualizarPlaya(EditText nombreBeach, EditText ubicacionBeach, EditText descripcionBeach, RatingBar ratingBar){
        String namePlaya = nombreBeach.getText().toString();
        String ubicPlaya = ubicacionBeach.getText().toString();
        String descripPlaya = descripcionBeach.getText().toString();
        float puntuacion = ratingBar.getRating();
        Map<String, Object> map = new HashMap<>();
        map.put("name",namePlaya);
        map.put("ubicacionPlaya", ubicPlaya);
        map.put("descripcionPlaya", descripPlaya);
        map.put("puntuacionPlaya", puntuacion);
        mfirestore.collection("beach").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "No creado", Toast.LENGTH_SHORT).show();
            }
        });




    }
}