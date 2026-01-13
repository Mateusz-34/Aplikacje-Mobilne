package com.example.sklep;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    TimePicker timePicker;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        btnConfirm = findViewById(R.id.btnConfirm);

        timePicker.setIs24HourView(true);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprawdzGodzine();
            }
        });
    }

    void sprawdzGodzine() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        LocalTime wybranyCzas = LocalTime.of(hour, minute);

        LocalTime otwarcie = LocalTime.of(8, 0);
        LocalTime zamkniecie = LocalTime.of(16, 0);

        if (wybranyCzas.isAfter(otwarcie) && wybranyCzas.isBefore(zamkniecie) || wybranyCzas.equals(otwarcie)) {
            Toast.makeText(this, "Otwarte, zapraszamy!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Zamknięte", Toast.LENGTH_SHORT).show();
        }
    }
}