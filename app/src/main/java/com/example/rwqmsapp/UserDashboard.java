package com.example.rwqmsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class UserDashboard extends AppCompatActivity implements ValueEventListener, View.OnClickListener {

    String Time;
    SimpleDateFormat simpleTimeFormat;
    Calendar calendar;
    private TextView waterTemperatureParameterTime, waterTurbidityParameterTime, waterPhParameterTime;
    private TextView waterTempVal, waterTurbidityVal, waterPhVal;
    private TextView waterTempStat, waterTurbidityStat, waterPhStat;



    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    private DatabaseReference waterTempVal1 = databaseReference.child("Monitoring").child("Sensing").child("Temperature");
    private DatabaseReference waterTurbidityVal1 = databaseReference.child("Monitoring").child("Sensing").child("Turbidity");
    private DatabaseReference waterPhVal1 = databaseReference.child("Monitoring").child("Sensing").child("pH_Value");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        waterTempVal = (TextView)findViewById(R.id.waterTemperatureParameterValue);
        waterTurbidityVal = (TextView)findViewById(R.id.waterTurbidityParameterValue);
        waterPhVal = (TextView)findViewById(R.id.waterPhParameterValue);

        waterTempStat = (TextView)findViewById(R.id.waterTemperatureParameterStatus);
        waterTurbidityStat = (TextView)findViewById(R.id.waterTurbidityParameterStatus);
        waterPhStat = (TextView)findViewById(R.id.waterPhParameterStatus);

        /*FOR DATE AND TIME IN DASHBOARD*/

        waterTemperatureParameterTime = findViewById(R.id.waterTemperatureParameterTime);
        waterTurbidityParameterTime = findViewById(R.id.waterTurbidityParameterTime);
        waterPhParameterTime = findViewById(R.id.waterPhParameterTime);
        calendar = Calendar.getInstance();
        simpleTimeFormat = new SimpleDateFormat("HH:mm");
        Time = simpleTimeFormat.format(calendar.getTime());

        waterTemperatureParameterTime.setText(Time);
        waterTurbidityParameterTime.setText(Time);
        waterPhParameterTime.setText(Time);

    }

    /*Retrieving Data from Database*/

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        if (snapshot.getValue(String.class)!=null){

            String key = snapshot.getKey();
            if (key.equals("Temperature")) {

                String first = snapshot.getValue(String.class);
                waterTempVal.setText(first + " C");



                try {

                    float firstNumVal = Float.valueOf(first);

                    if (firstNumVal < 0) {

                        waterTempStat.setText("NORMAL");
                        waterTempStat.setTextColor(Color.parseColor("#5a96fe"));

                    }

                    if ((firstNumVal >= 10) && ( firstNumVal <= 22)) {

                        waterTempStat.setText("NORMAL");
                        waterTempStat.setTextColor(Color.parseColor("#5a96fe"));

                    }

                    if ((firstNumVal >= 23) && ( firstNumVal <= 35)) {

                        waterTempStat.setText("CONDITIONAL");
                        waterTempStat.setTextColor(Color.parseColor("#fedb5b"));

                    }

                    if (firstNumVal >= 36){

                        waterTempStat.setText("HIGH");
                        waterTempStat.setTextColor(Color.parseColor("#FF0000"));

                    }

                }

                catch (NumberFormatException ex){

                }


            }

            if (key.equals("Turbidity")) {

                String second = snapshot.getValue(String.class);
                waterTurbidityVal.setText(second + " NTU");


                try {

                    float firstNumVal = Float.valueOf(second);

                    if (firstNumVal < 0) {

                        waterTurbidityStat.setText("NORMAL");
                        waterTurbidityStat.setTextColor(Color.parseColor("#5a96fe"));


                    }

                    if ((firstNumVal >= 1) && ( firstNumVal <= 5)) {

                        waterTurbidityStat.setText("NORMAL");
                        waterTurbidityStat.setTextColor(Color.parseColor("#5a96fe"));

                    }

                    if ((firstNumVal >= 6) && ( firstNumVal <= 10)) {

                        waterTurbidityStat.setText("CONDITIONAL");
                        waterTurbidityStat.setTextColor(Color.parseColor("#fedb5b"));

                    }

                    if (firstNumVal > 11) {

                        waterTurbidityStat.setText("HIGH");
                        waterTurbidityStat.setTextColor(Color.parseColor("#FF0000"));

                    }

                }

                catch (NumberFormatException ex){

                }


            }

            if (key.equals("pH_Value")) {

                String third = snapshot.getValue(String.class);
                waterPhVal.setText(third + " pH");

                try {

                    float firstNumVal = Float.valueOf(third);

                    if (firstNumVal < 3.6) {

                        waterPhStat.setText("POOR");
                        waterPhStat.setTextColor(Color.parseColor("#fdab59"));

                    }

                    if ((firstNumVal >= 3.7) && ( firstNumVal <= 8.5)) {

                        waterPhStat.setText("NORMAL");
                        waterPhStat.setTextColor(Color.parseColor("#5a96fe"));

                    }

                    if ((firstNumVal >= 8.6) && ( firstNumVal <= 11)) {

                        waterPhStat.setText("CONDITIONAL");
                        waterPhStat.setTextColor(Color.parseColor("#fedb5b"));

                    }

                    if (firstNumVal >= 12) {

                        waterPhStat.setText("HIGH");
                        waterPhStat.setTextColor(Color.parseColor("#FF0000"));

                    }

                }

                catch (NumberFormatException ex){

                }

            }
        }


    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        waterTempVal1.addValueEventListener(this);
        waterTurbidityVal1.addValueEventListener(this);
        waterPhVal1.addValueEventListener(this);

    }

    @Override
    public void onClick(View view) {




    }
}



