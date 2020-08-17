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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    EditText edtuserName, edtemailId, edtpassword, edtconfirmPassword, edtPhoneNumber;
    Button btnregister;
    String name, email, password, confirmpassword, phoneNumber;

    FirebaseAuth mAuth;

    DatabaseReference myRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        edtuserName = findViewById(R.id.editTextUserNameField);
        edtemailId = findViewById(R.id.editTextEmailIdField);
        edtpassword = findViewById(R.id.editTextPasswordField);
        edtconfirmPassword = findViewById(R.id.editTextConfirmPasswordField);
        edtPhoneNumber = findViewById(R.id.editTextPhoneNumberField);

        mAuth = FirebaseAuth.getInstance();

        myRef = FirebaseDatabase.getInstance().getReference().child("User_Data");

        btnregister = findViewById(R.id.buttonRegister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = edtuserName.getText().toString().trim();
                email = edtemailId.getText().toString().trim();
                password = edtpassword.getText().toString().trim();
                confirmpassword = edtconfirmPassword.getText().toString().trim();
                phoneNumber = edtPhoneNumber.getText().toString().trim();


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
                }  else if(phoneNumber.isEmpty() && phoneNumber.length() < 10) {
                    Toast.makeText(RegistrationActivity.this, "Please enter a Valid Phone Number", Toast.LENGTH_SHORT).show();
                }else {
                    next();
                    userReg();
                }

            }
        });

    }

    private void userReg() {

        RegUser regUser = new RegUser(name, email, phoneNumber);

        String key = myRef.push().getKey();

        // myRef is Pointing to User_Data Child
        // Child will be created for User_Data with Id
        myRef.child(key).setValue(regUser);

        Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);

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