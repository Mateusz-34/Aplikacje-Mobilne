package com.example.rezerwacjawizyty;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button btnConfirm;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        btnConfirm = findViewById(R.id.btnConfirm);
        tvResult = findViewById(R.id.tvResult);

        timePicker.setIs24HourView(true);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDate();
            }
        });
    }

    private void checkDate() {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        LocalDate selectedDate = LocalDate.of(year, month + 1, day);
        LocalTime selectedTime = LocalTime.of(hour, minute);

        LocalDateTime selectedDateTime = LocalDateTime.of(selectedDate, selectedTime);
        LocalDateTime now = LocalDateTime.now();

        if (selectedDateTime.isBefore(now)) {
            tvResult.setText("Błąd: Wybrano termin z przeszłości!");
            tvResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy 'o godzinie' HH:mm");
            String message = "Zarezerwowano: " + selectedDateTime.format(formatter);

            tvResult.setText(message);
            tvResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }
    }
}