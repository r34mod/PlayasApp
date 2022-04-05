package com.example.playasapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.playasapp.Login;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.moveup);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.movedown);

        TextView txtViewAnimation = findViewById(R.id.textViewAnimation);
        ImageView imgViewAnimation = findViewById(R.id.imageViewAnimation);

        txtViewAnimation.setAnimation(animation2);

        imgViewAnimation.setAnimation(animation1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }
}
