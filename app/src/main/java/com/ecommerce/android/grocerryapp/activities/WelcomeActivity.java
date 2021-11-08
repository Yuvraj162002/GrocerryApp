package com.ecommerce.android.grocerryapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.android.grocerryapp.MainActivity;
import com.ecommerce.android.grocerryapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class WelcomeActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        auth = FirebaseAuth.getInstance();
        TextView login = findViewById(R.id.login);
        TextView registartion = findViewById(R.id.regis);
        ProgressBar progressBar = findViewById(R.id.progressbarwelcome);
        progressBar.setVisibility(View.GONE);

        if (auth.getCurrentUser() != null) {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            Toast.makeText(this, "Please wait you are already login in", Toast.LENGTH_SHORT).show();
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


     registartion.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent =  new Intent(WelcomeActivity.this, RegistartionActivity.class);
             startActivity(intent);

         }
     });
      }
}