package com.example.playasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Clase Register al igual que el login, el usuario debera registrarse mediante email y una password
 *
 * Estos datos se almacenan en Firebase para usarlos en el login y en el perfil del user
 *
 * Se ha utilizado FireStore para el registro de usuarios
 *
 *
 */

public class Register extends AppCompatActivity {

    Button btn_register;
    EditText name, email, password, phone;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://proyectoplayas-6aaa0-default-rtdb.europe-west1.firebasedatabase.app/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Registro");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name = findViewById(R.id.nombre);
        email = findViewById(R.id.correo);
        password = findViewById(R.id.contrasena);
        btn_register = findViewById(R.id.btn_registro);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameUser = name.getText().toString().trim();
                String emailUser = email.getText().toString().trim();
                String passUser = password.getText().toString().trim();
                String phoneUser = phone.getText().toString().trim();


                if(nameUser.isEmpty() || emailUser.isEmpty() || passUser.isEmpty()){
                    Toast.makeText(Register.this, "Error de datos", Toast.LENGTH_SHORT).show();
                }

                databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(phoneUser)){
                            Toast.makeText(Register.this, "Telefono registrado", Toast.LENGTH_SHORT).show();
                        }else{
                            databaseReference.child("user").child(phoneUser).child("nombre").setValue(nameUser);
                            databaseReference.child("user").child(phoneUser).child("email").setValue(emailUser);
                            databaseReference.child("user").child(phoneUser).child("password").setValue(passUser);
                            Toast.makeText(Register.this, "Registrado", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(Register.this, Login.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });
    }

    /**
     *
     * Funcion de registrar el usuario, donde se le pasan los parametros pedidos al usuario.
     *
     * En este proceso, se verifican que esten correctos y se le asigna un ID al usuario registrado
     *
     * Con este id del usuario, se crea una columna en Firebase con sus datos
     *
    */



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}