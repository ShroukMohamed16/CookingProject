package com.example.cookingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            if(firebaseAuth.getCurrentUser() == null) {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(SplashScreenActivity.this, "Welcome", Toast.LENGTH_SHORT).show();

            }
        },5000);
    }

}