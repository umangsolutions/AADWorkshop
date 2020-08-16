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

public class RegistrationActivity extends AppCompatActivity {

    EditText edtuserName, edtemailId, edtpassword, edtconfirmPassword;
    Button btnregister;
    String name, email, password, confirmpassword;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtuserName = findViewById(R.id.editTextUserNameField);
        edtemailId = findViewById(R.id.editTextEmailIdField);
        edtpassword = findViewById(R.id.editTextPasswordField);
        edtconfirmPassword = findViewById(R.id.editTextConfirmPasswordField);

        mAuth = FirebaseAuth.getInstance();

        btnregister = findViewById(R.id.buttonRegister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = edtuserName.getText().toString().trim();
                email = edtemailId.getText().toString().trim();
                password = edtpassword.getText().toString().trim();
                confirmpassword = edtconfirmPassword.getText().toString().trim();

                if(name.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                } else if(email.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please enter EMail ID", Toast.LENGTH_SHORT).show();
                } else if(password.isEmpty() && password.length() < 8) {
                    Toast.makeText(RegistrationActivity.this, "Please enter a Valid password", Toast.LENGTH_SHORT).show();
                } else if(confirmpassword.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please enter COnfirm pasword", Toast.LENGTH_SHORT).show();
                } else if(!password.equals(confirmpassword)) {
                    Toast.makeText(RegistrationActivity.this, "Password and Conf Password should same", Toast.LENGTH_SHORT).show();
                } else {
                    next();
                }

            }
        });

    }

    private void next() {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(RegistrationActivity.this, "User registration successful !", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                } else {
                    Toast.makeText(RegistrationActivity.this, "Something Wrong Occurred !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}