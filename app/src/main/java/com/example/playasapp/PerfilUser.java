package com.example.playasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.playasapp.clases.Users;
import com.example.playasapp.Login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 *
 * Clase PerfilUser conectada a Firebase para mostrar la informacion del usuario
 *
 * En esta pantalla el usuario podra modificar algunos de sus datos, excepto el email
 * ya quue es el identificador del user
 *
 */


public class PerfilUser extends AppCompatActivity {


    DatabaseReference reference;
    EditText nameUser, photoEdit, emailEdit;
    TextView emailUser,nombreUser;
    Button btnUpdate, btnDelete;
    ImageButton btnSalir;
    ImageView photoUser;



    @Override
    protected void onCreate(Bundle savedInstancedState){
        super.onCreate(savedInstancedState);
        setContentView(R.layout.activity_update_profile);
        reference = FirebaseDatabase.getInstance().getReference("Users");
        nameUser = findViewById(R.id.nameUser);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        emailUser = findViewById(R.id.txtEmailUser);
        nombreUser = findViewById(R.id.nombre_user);
        btnSalir = findViewById(R.id.btn_salir);

        nombreUser.setText("Juana");
        emailUser.setText("Juana@gmail.com");


        //Glade.with


        btnSalir.setOnClickListener((view)->{
            Intent i = new Intent(PerfilUser.this, Ajustes.class);
            startActivity(i);
            Toast.makeText(this, "SALIENDO DEL PERFIL", Toast.LENGTH_SHORT).show();
        });

        btnDelete.setOnLongClickListener((view)->{
            //reference = FirebaseDatabase.getInstance().getReference("Users").child(id);
            //reference.removeValue();
            Toast.makeText(this, "ELIMINADO EL USER", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(PerfilUser.this, Login.class);
            return true;
        });

        btnUpdate.setOnClickListener((view)->{
            String txtName = nameUser.getText().toString();
            //FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            //DatabaseReference databaseReference = firebaseDatabase.getReference("Users");
            //databaseReference.child(fireuser.getUid()).child("username").setValue(txtName);
        });
    }


}
