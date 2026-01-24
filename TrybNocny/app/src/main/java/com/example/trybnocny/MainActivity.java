package com.example.trybnocny;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout rootLayout = findViewById(R.id.rootLayout);
        Switch switchDark = findViewById(R.id.switchDark);
        TextView textArticle = findViewById(R.id.textArticle);

        rootLayout.setBackgroundColor(Color.WHITE);
        textArticle.setTextColor(Color.BLACK);
        switchDark.setTextColor(Color.BLACK);

        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rootLayout.setBackgroundColor(Color.BLACK);
                textArticle.setTextColor(Color.WHITE);
                switchDark.setTextColor(Color.WHITE);
            } else {
                rootLayout.setBackgroundColor(Color.WHITE);
                textArticle.setTextColor(Color.BLACK);
                switchDark.setTextColor(Color.BLACK);
            }
        });
    }
}
