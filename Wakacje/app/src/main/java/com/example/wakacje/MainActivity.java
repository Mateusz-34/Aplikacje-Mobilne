package com.example.wakacje;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Button btnConfirm;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        btnConfirm = findViewById(R.id.btnConfirm);
        tvResult = findViewById(R.id.tvResult);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDate();
            }
        });
    }

    private void checkDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        LocalDate selectedDate = LocalDate.of(year, month, day);

        LocalDate today = LocalDate.now();

        long days = ChronoUnit.DAYS.between(today, selectedDate);

        if (days < 0) {
            tvResult.setText("Ta data już minęła!");
        } else {
            tvResult.setText("Pozostało " + days + " dni");
        }
    }
}