package com.example.playasapp.adds;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.playasapp.R;
import com.example.playasapp.adapters.AdapPiscinas;
import com.example.playasapp.adapters.AdapPlayas;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
 *
 * Clase Para agregar piscinas a la base de datos de Firebase
 *
 * El usuario registrado podra agregar una playa a la app, agregando informacion de ella
 *
 * Tambien ademas de agregar podra eliminar alguna de las piscinas creadas por el.
 *
 * NO PUEDE ELIMINAR PLAYAS DE OTROS USUARIOS!
 *
 *
 */


public class AddPoolsActivity extends AppCompatActivity {

    private FirebaseFirestore mfirestore;

    TextView nombre, descripcion, ubicacion;
    ImageButton img_salir;
    ImageView imgPiscina;
    Button btnAddPool, btnDeletPool;
    EditText nombrePool, descripcionPool, ubicacionPool;



    private RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pool);
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


        ratingBar = (RatingBar) findViewById(R.id.ratingBarPool);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(AddPoolsActivity.this, "La puntacion dada es: " + rating, Toast.LENGTH_SHORT).show();
            }
        });



        nombre = findViewById(R.id.nameTxtPool);
        nombrePool = findViewById(R.id.namePool);

        descripcion = findViewById(R.id.descriptTxtPool);
        descripcionPool = findViewById(R.id.descriptPool);

        ubicacion = findViewById(R.id.ubicationTxtPool);
        ubicacionPool = findViewById(R.id.ubicationPool);

        img_salir = findViewById(R.id.btn_salir_pool);
        btnAddPool = findViewById(R.id.addPool);

        btnDeletPool = findViewById(R.id.deletePool);

        imgPiscina = findViewById(R.id.photoPool);


        img_salir.setOnClickListener(v -> {
            Intent i = new Intent(AddPoolsActivity.this, AdapPiscinas.class);
            startActivity(i);
        });

        btnAddPool.setOnClickListener(v -> {
            actualizarPiscina(nombrePool, ubicacionPool, descripcionPool, ratingBar);
        });

        btnDeletPool.setOnClickListener(v -> {
            String id = mfirestore.collection("pool").getId();
            deletePool(id);
            Intent i = new Intent(AddPoolsActivity.this, AdapPiscinas.class);
            startActivity(i);
        });
    }

        private void deletePool(String id){
            mfirestore.collection("pool").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            });

        }


    /**
     *
     * Agrega una piscina a la bbdd, tambien sirve para actualizar la informacion de la misma
     *
     * @param nombrePool
     * @param ubicacionPool
     * @param descripcionPool
     * @param ratingBar
     */
    private void actualizarPiscina(EditText nombrePool, EditText ubicacionPool, EditText descripcionPool, RatingBar ratingBar){
        String namePool = nombrePool.getText().toString();
        String ubicPool = ubicacionPool.getText().toString();
        String descripPool = descripcionPool.getText().toString();
        float puntuacion = ratingBar.getRating();

        Map<String, Object> map = new HashMap<>();
        map.put("name",namePool);
        map.put("ubicacionPool", ubicPool);
        map.put("descripcionPool", descripPool);
        map.put("puntuacionPool", puntuacion);
        /**Hacemos referencia a la tabla Pool, y comprobamos que la id del usuario este asociada
            a la id de la pool en concreta (child(fireUser.getUid())

         getUid() es la key principal del usuario!!
        */
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
