package com.example.ocenaaplikacji;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView tvRatingResult;
    private TextView tvRatingResult2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.ratingBar);
        tvRatingResult = findViewById(R.id.tvRatingResult);
        tvRatingResult2 = findViewById(R.id.tvRatingResult2);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating <= 2.5) {
                    tvRatingResult.setText("Słabo");
                    tvRatingResult2.setText("Ocena: " + rating + " / 5");

                    tvRatingResult.setTextColor(Color.RED);
                    tvRatingResult2.setTextColor(Color.RED);
                } else if (rating > 2.5 && rating < 4.0){
                    tvRatingResult.setText("OK");
                    tvRatingResult2.setText("Ocena: " + rating + " / 5");

                    tvRatingResult.setTextColor(Color.rgb(255, 165, 0));
                    tvRatingResult2.setTextColor(Color.rgb(255, 165, 0));
                } else if (rating >= 4.0){
                    tvRatingResult.setText("Super!");
                    tvRatingResult2.setText("Ocena: " + rating + " / 5");

                    tvRatingResult.setTextColor(Color.GREEN);
                    tvRatingResult2.setTextColor(Color.GREEN);
                }
            }
        });
    }
}