package com.example.rejestratorsnu;

import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.Duration;
import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    private Button btnSleepStart, btnSleepEnd, btnCalculate;
    private TextView tvSleepDuration;

    private LocalTime czasStart = null;
    private LocalTime czasStop = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSleepStart = findViewById(R.id.btnSleepStart);
        btnSleepEnd = findViewById(R.id.btnSleepEnd);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvSleepDuration = findViewById(R.id.tvSleepDuration);

        btnSleepStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(true);
            }
        });

        btnSleepEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker(false);
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateSleepDuration();
            }
        });
    }

    private void showTimePicker(final boolean isStart) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (isStart) {
                            czasStart = LocalTime.of(hourOfDay, minute);
                            tvSleepDuration.setText("Czas pójścia spać ustawiony: " + String.format("%02d:%02d", hourOfDay, minute));
                        } else {
                            czasStop = LocalTime.of(hourOfDay, minute);
                            tvSleepDuration.setText("Czas wstania ustawiony: " + String.format("%02d:%02d", hourOfDay, minute));
                        }
                    }
                },
                22, 0, true
        );
        timePickerDialog.show();
    }

    private void calculateSleepDuration() {
        if (czasStart == null || czasStop == null) {
            Toast.makeText(this, "Ustaw czas pójścia spać i wstania!", Toast.LENGTH_SHORT).show();
            return;
        }

        LocalTime start = czasStart;
        LocalTime stop = czasStop;

        if (stop.isBefore(start)) {
            stop = stop.plusHours(24);
        }

        Duration duration = Duration.between(start, stop);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        tvSleepDuration.setText("Czas snu: " + hours + " godzin " + minutes + " minut");
    }
}
