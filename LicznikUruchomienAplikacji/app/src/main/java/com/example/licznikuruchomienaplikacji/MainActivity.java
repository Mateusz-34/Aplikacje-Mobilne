package com.example.licznikuruchomienaplikacji;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "my_prefs";
    private static final String KEY_COUNTER = "launch_counter";

    private TextView textCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textCounter = findViewById(R.id.text_counter);
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        int count = prefs.getInt(KEY_COUNTER, 0);

        count++;

        prefs.edit().putInt(KEY_COUNTER, count).apply();

        textCounter.setText("Liczba uruchomień: " + count);
    }
}
