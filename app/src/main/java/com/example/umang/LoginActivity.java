package com.example.umang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText name, password;
    Button login;
    String userName, userPassword;

    TextView txtCreate;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.editTextEmailId);
        password = findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();

        txtCreate = findViewById(R.id.createUser);

        txtCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);

            }
        });

        login = findViewById(R.id.buttonlogIn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = name.getText().toString().trim();
                userPassword = password.getText().toString().trim();

                if(userName.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter Email ID", Toast.LENGTH_SHORT).show();
                } else if(userPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    next();
                }
            }
        });

    }

    private void next() {
        mAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Logged in Successfully !", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}