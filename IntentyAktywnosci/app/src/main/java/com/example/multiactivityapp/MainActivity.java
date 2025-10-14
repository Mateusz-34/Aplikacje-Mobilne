package com.example.intentyaktywnosci;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String IntentName = "name";
    public static final String IntentSurname = "surname";
    public static final String IntentEmail = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewSurname = findViewById(R.id.textViewSurname);
        TextView textViewEmail = findViewById(R.id.textViewEmail);

        Button buttonNavigate = findViewById(R.id.buttonNavigate);

        buttonNavigate.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        String name = getIntent().getStringExtra(IntentName);
        String surname = getIntent().getStringExtra(IntentSurname);
        String email = getIntent().getStringExtra(IntentEmail);

        if (name != null) {
            textViewName.setText("Pole Name: " + name);
        }
        if (surname != null) {
            textViewSurname.setText("Pole Surname: " + surname);
        }
        if (email != null) {
            textViewEmail.setText("Pole Email: " + email);
        }
    }
}