package com.example.umang;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {

    TextView txtName,txtEmail,txtPhone;

    String userEmail,name,emailID,phone;

    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEMail);
        txtPhone =findViewById(R.id.txtPhone);

        myRef = FirebaseDatabase.getInstance().getReference("User_Data");

        userEmail = getIntent().getStringExtra("email");

        Query query = myRef.orderByChild("email").equalTo(userEmail);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    name = Objects.requireNonNull(snapshot1.getValue(RegUser.class)).getName();
                    emailID = Objects.requireNonNull(snapshot1.getValue(RegUser.class)).getEmail();
                    phone = Objects.requireNonNull(snapshot1.getValue(RegUser.class)).getPhoneNumber();
                }

                txtName.setText(name);
                txtEmail.setText(emailID);
                txtPhone.setText(phone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(WelcomeActivity.this, "Some Error has Occurred !", Toast.LENGTH_SHORT).show();
            }
        });







    }
}