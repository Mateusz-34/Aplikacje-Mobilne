package com.example.przekazywaniedanych;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_BUTTON = "com.example.przekazywaniedanych.BUTTON";
    public static final String EXTRA_ADD1 = "com.example.przekazywaniedanych.ADD1";
    public static final String EXTRA_ADD2 = "com.example.przekazywaniedanych.ADD2";
    public static final String EXTRA_NAME1 = "com.example.przekazywaniedanych.NAME1";
    public static final String EXTRA_NAME2 = "com.example.przekazywaniedanych.NAME2";
    public static final String EXTRA_AGE = "com.example.przekazywaniedanych.AGE";
    public static final String EXTRA_SWITCH_STATE = "com.example.przekazywaniedanych.SWITCH_STATE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        EditText number1 = findViewById(R.id.number1);
        EditText number2 = findViewById(R.id.number2);
        Button add = findViewById(R.id.add);

        EditText name1 = findViewById(R.id.name1);
        Button send = findViewById(R.id.send);

        EditText name2 = findViewById(R.id.name2);
        EditText age = findViewById(R.id.age);
        Button sendData = findViewById(R.id.sendData);

        Switch premiumSwitch = findViewById(R.id.premiumSwitch);
        Button saveButton = findViewById(R.id.saveButton);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(EXTRA_BUTTON, "Dane zostały pomyślnie przekazane!");

                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                int liczba1 = Integer.parseInt(number1.getText().toString());
                int liczba2 = Integer.parseInt(number2.getText().toString());

                intent.putExtra(EXTRA_ADD1, liczba1);
                intent.putExtra(EXTRA_ADD2, liczba2);

                startActivity(intent);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);

                String imie = name1.getText().toString();

                intent.putExtra(EXTRA_NAME1, imie);

                startActivity(intent);
            }
        });

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imie = name2.getText().toString();
                int wiek = Integer.parseInt(age.getText().toString());

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(EXTRA_NAME2, imie);
                intent.putExtra(EXTRA_AGE, wiek);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isPremiumOn = premiumSwitch.isChecked();

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(EXTRA_SWITCH_STATE, isPremiumOn);
                startActivity(intent);
            }
        });
    }
}