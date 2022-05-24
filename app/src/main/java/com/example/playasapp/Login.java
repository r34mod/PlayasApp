package com.example.playasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 *
 * Clase Login donde acceder con tu usuario a la app, en modo anonimo o registrarte
 *
 * Proceso de login va por correo y contrasenia
 *
 *
 */

public class Login extends AppCompatActivity {

    Button btn_login, btn_register, btn_login_anonymous, btn_google;
    EditText password, phone;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        this.setTitle("Login");



        phone = findViewById(R.id.phone);
        password = findViewById(R.id.contrasena);
        btn_login = findViewById(R.id.btn_ingresar);
        btn_register = findViewById(R.id.btn_register);
        btn_login_anonymous = findViewById(R.id.btn_anonymous);
        btn_google = findViewById(R.id.btn_google);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneUser = phone.getText().toString().trim();

                String passUser = password.getText().toString().trim();

                if (phoneUser.isEmpty() && passUser.isEmpty()){
                    Toast.makeText(Login.this, "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                   databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {

                           if(snapshot.hasChild(phoneUser)){
                               final String pwd = snapshot.child(phoneUser).child("password").getValue(String.class);
                               if(pwd.equals(passUser)){
                                   Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(Login.this, Ajustes.class));
                               }
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });

        btn_login_anonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAnonymous();
            }
        });

        btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, LoginPrueba.class));
            }
        });
    }

    /**
     *
     * Funcion para el usuario que no tenga cuenta y tampoco quiera registrarse,
     * obtiene un usuario anonimo para acceder a la app
     *
     * Se inicia la sesion sin dejar una key de userID
     *
     */

    private void loginAnonymous() {
        //FORZAMOS POR AHORA LA ENTRADA ANONIMA
        Intent i = new Intent(this, Ajustes.class);
        startActivity(i);


    }


    /**
     *
     * Funcion de login via Firebase pasando como params email y password del usuario
     *
     * Miramos que las credenciales son correctas y si lo son, le damos acceso a la pagina principal
     *
     * (Ajustes.class)
     *
     * Sino, no tendra acceso y se queda en la pagina de Login.class
     *
     * @param emailUser
     * @param passUser
     */


}