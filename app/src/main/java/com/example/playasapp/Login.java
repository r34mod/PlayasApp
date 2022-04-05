package com.example.playasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.firebase.ui.*;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {

    private FirebaseAuth authfire;
    private FirebaseAuth.AuthStateListener authStateListener;

    public static final int SIGN_IN = 1;

    List<AuthUI.IdpConfig> provider = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build()
            //new AuthUI.IdpConfig.FacebookBuilder().build()
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.startTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        authfire = FirebaseAuth.getInstance();
        authStateListener = firebaseAuth -> {
            FirebaseUser usuario = firebaseAuth.getCurrentUser();
            if(usuario != null){
                moverMain();
                Toast.makeText(Login.this, "Log in", Toast.LENGTH_SHORT).show();
            }else{
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(provider).setIsSmartLockEnabled(false).build(), SIGN_IN);

            }
        };
    }


    @Override
    protected void onResume(){
        super.onResume();
        authfire.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onPause(){
        super.onPause();
        authfire.removeAuthStateListener(authStateListener);
    }

    private void moverMain(){
        Intent a = new Intent(this, MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}