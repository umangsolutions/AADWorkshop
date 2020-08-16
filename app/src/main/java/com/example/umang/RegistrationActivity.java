package com.example.umang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtuserName, edtemailId, edtpassword, edtconfirmPassword;
    Button btnregister;
    String name, email, password, confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edtuserName = findViewById(R.id.editTextUserNameField);
        edtemailId = findViewById(R.id.editTextEmailIdField);
        edtpassword = findViewById(R.id.editTextPasswordField);
        edtconfirmPassword = findViewById(R.id.editTextConfirmPasswordField);

        btnregister = findViewById(R.id.buttonRegister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = edtemailId.getText().toString().trim();
                password = edtpassword.getText().toString().trim();
                confirmpassword = edtconfirmPassword.getText().toString().trim();

                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



    }
}