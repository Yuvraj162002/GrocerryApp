package com.ecommerce.android.grocerryapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.android.grocerryapp.R;
import com.ecommerce.android.grocerryapp.model.Usermodel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistartionActivity extends AppCompatActivity {

    EditText email;
    EditText name;
    EditText password;
    Button SignUp;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registartion);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        progressBar = findViewById(R.id.progerssregis);
        progressBar.setVisibility(View.GONE);
        TextView textView = findViewById(R.id.siginlogin);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        SignUp = findViewById(R.id.signup);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistartionActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void createUser() {
        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userpass = password.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(useremail)){
            Toast.makeText(this, "Please enter a email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userpass)){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userpass.length()<6){
            Toast.makeText(this, "Password must be greater than 6 words", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create User

        auth.createUserWithEmailAndPassword(useremail,userpass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                           Usermodel usermodel = new Usermodel(useremail,userpass,username);
                           String id = task.getResult().getUser().getUid();
                           firebaseDatabase.getReference().child("User").child(id).setValue(usermodel);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistartionActivity.this, "Registration Succefull", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistartionActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}