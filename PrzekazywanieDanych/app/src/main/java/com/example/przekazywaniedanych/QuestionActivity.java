package com.example.przekazywaniedanych;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "com.example.przekazywaniedanych.SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Button answer1 = findViewById(R.id.answer1);
        Button answer2 = findViewById(R.id.answer2);
        Button answer3 = findViewById(R.id.answer3);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME1);

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultActivity(name, 0);
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultActivity(name, 5);
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultActivity(name, 0);
            }
        });
    }

    private void openResultActivity(String name, int score) {
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);

        intent.putExtra(MainActivity.EXTRA_NAME1, name);
        intent.putExtra(EXTRA_SCORE, score);

        startActivity(intent);
    }
}
