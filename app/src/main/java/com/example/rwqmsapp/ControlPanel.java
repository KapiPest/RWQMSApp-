package com.example.rwqmsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ControlPanel extends AppCompatActivity implements View.OnClickListener {

    public ImageView dashboardBtn, contactsBtn, analyticsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);



        dashboardBtn = (ImageView) findViewById(R.id.dashboardIcon);
        contactsBtn = (ImageView) findViewById(R.id.contactsIcon);
        analyticsBtn = (ImageView) findViewById(R.id.analyticsIcon);


        dashboardBtn.setOnClickListener((View.OnClickListener)this);
        contactsBtn.setOnClickListener((View.OnClickListener)this);
        analyticsBtn.setOnClickListener((View.OnClickListener) this);
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

            case R.id.analyticsIcon:Icon:
            i = new Intent(this, Analytics.class);
                startActivity(i);
                break;

        }
    }
}
