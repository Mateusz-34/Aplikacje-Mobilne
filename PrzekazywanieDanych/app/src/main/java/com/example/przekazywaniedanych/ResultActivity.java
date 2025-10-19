package com.example.przekazywaniedanych;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultText = findViewById(R.id.resultText);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME1);
        int score = intent.getIntExtra(QuestionActivity.EXTRA_SCORE, 0);

        String message = "Gratulacje " + name + ", zdobyłeś " + score + " punktów!";
        resultText.setText(message);
    }
}
