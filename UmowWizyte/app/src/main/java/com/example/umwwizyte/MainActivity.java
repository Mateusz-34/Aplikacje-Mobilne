package com.example.umwwizyte;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnPickDateTime;
    private TextView tvSelectedDateTime;

    private int selectedYear, selectedMonth, selectedDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPickDateTime = findViewById(R.id.btnPickDateTime);
        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime);

        btnPickDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedYear = year;
                        selectedMonth = monthOfYear;
                        selectedDay = dayOfMonth;
                        showTimePicker();
                    }
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    private void showTimePicker() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        LocalDateTime dateTime = LocalDateTime.of(
                                selectedYear,
                                selectedMonth + 1,
                                selectedDay,
                                hourOfDay,
                                minute
                        );

                        if (dateTime.isBefore(LocalDateTime.now())) {
                            Toast.makeText(
                                    MainActivity.this,
                                    "Nie można cofnąć czasu!",
                                    Toast.LENGTH_SHORT
                            ).show();
                        } else {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                                    "EEEE, d MMMM yyyy 'o' HH:mm",
                                    new Locale("pl", "PL")
                            );
                            tvSelectedDateTime.setText("Umówiono: " + dateTime.format(formatter));
                        }
                    }
                },
                hour, minute, true
        );

        timePickerDialog.show();
    }
}