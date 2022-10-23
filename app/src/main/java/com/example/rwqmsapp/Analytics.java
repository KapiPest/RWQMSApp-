package com.example.rwqmsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Analytics extends AppCompatActivity implements View.OnClickListener {

    public ImageView dashboardBtn, contactsBtn, sensorsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        dashboardBtn = (ImageView) findViewById(R.id.dashboardIcon);
        contactsBtn = (ImageView) findViewById(R.id.contactsIcon);
        sensorsBtn = (ImageView) findViewById(R.id.sensorsIcon);


        dashboardBtn.setOnClickListener((View.OnClickListener)this);
        contactsBtn.setOnClickListener((View.OnClickListener)this);
        sensorsBtn.setOnClickListener((View.OnClickListener) this);


    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {

            case R.id.dashboardIcon:Icon:
                i = new Intent(this, UserDashboard.class);
                startActivity(i);
                break;

            case R.id.contactsIcon:Icon:
            i = new Intent(this, Contacts.class);
                startActivity(i);
                break;

            case R.id.sensorsIcon:Icon:
            i = new Intent(this, ControlPanel.class);
                startActivity(i);
                break;

        }
    }
}
