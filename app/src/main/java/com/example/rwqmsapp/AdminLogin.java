package com.example.rwqmsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        CheckBox showPassword;
        EditText username = findViewById(R.id.adminUsername);
        EditText password = findViewById(R.id.adminPassword);
        MaterialButton login_btn = (MaterialButton) findViewById(R.id.adminloginbtn);
        showPassword = findViewById(R.id.adminShowPassword);

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usernameTxt = username.getText().toString();
                final String passwordTxt = password.getText().toString();

                if (usernameTxt.isEmpty() || passwordTxt.isEmpty()) {

                    Toast.makeText(AdminLogin.this, "Please enter your Username and Password", Toast.LENGTH_SHORT).show();

                }

                else {

                    databaseReference.child("Admin").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(usernameTxt)){

                                final String getPassword = snapshot.child(usernameTxt).child("password").getValue(String.class);

                                if (getPassword.equals(passwordTxt)){

                                    Toast.makeText(AdminLogin.this, "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(AdminLogin.this, AdminDashboard.class));
                                    finish();

                                }

                                else {
                                    Toast.makeText(AdminLogin.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                Toast.makeText(AdminLogin.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {



                        }
                    });
                }
            }
        });

    }
}