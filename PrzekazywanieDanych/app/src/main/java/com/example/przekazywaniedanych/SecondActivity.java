package com.example.przekazywaniedanych;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();

        TextView textView = findViewById(R.id.textView);

        String text = intent.getStringExtra(MainActivity.EXTRA_BUTTON);
        textView.setText(text);


        TextView wynik = findViewById(R.id.wynik);

        int number1 = intent.getIntExtra(MainActivity.EXTRA_ADD1, 0);
        int number2 = intent.getIntExtra(MainActivity.EXTRA_ADD2, 0);

        int sum = number1 + number2;
        wynik.setText(String.valueOf(sum));


        TextView textView1 = findViewById(R.id.textView1);

        String name = intent.getStringExtra(MainActivity.EXTRA_NAME2);
        int age = intent.getIntExtra(MainActivity.EXTRA_AGE, 0);

        String message = "Imię: " + name + ", Wiek: " + age;
        textView1.setText(message);

        TextView premiumSwitch = findViewById(R.id.premiumSwitch);
        boolean isPremiumOn = intent.getBooleanExtra(MainActivity.EXTRA_SWITCH_STATE, false);

        if (isPremiumOn) {
            premiumSwitch.setText("Ustawienia premium: Włączone");
        } else {
            premiumSwitch.setText("Ustawienia premium: Wyłączone");
        }
    }
}