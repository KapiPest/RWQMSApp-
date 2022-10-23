package com.example.rwqmsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;


public class MainActivity extends AppCompatActivity {

    Button button;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(MainActivity.this);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_border_for_table));
        dialog.setContentView(R.layout.popup);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        Button admin = dialog.findViewById(R.id.btn_admin);
        Button guest = dialog.findViewById(R.id.btn_guest);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent admin = new Intent(MainActivity.this,AdminLogin.class);
                startActivity(admin);

            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent guest = new Intent(MainActivity.this,UsersWelcoming.class);
                startActivity(guest);

            }
        });

        button = findViewById(R.id.pop_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

            }
        });
    }
}