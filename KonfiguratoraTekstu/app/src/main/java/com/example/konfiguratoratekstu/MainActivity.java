package com.example.konfiguratoratekstu;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvSample, tvRatingResult;
    private SeekBar sbSize;
    private Switch swVisibility;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSample = findViewById(R.id.tvSample);
        tvRatingResult = findViewById(R.id.tvRatingResult);
        sbSize = findViewById(R.id.sbSize);
        swVisibility = findViewById(R.id.swVisibility);
        ratingBar = findViewById(R.id.ratingBar);

        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float newSize = progress + 10;
                tvSample.setTextSize(newSize);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        swVisibility.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvSample.setVisibility(View.VISIBLE);
                    swVisibility.setText("Tekst widoczny");
                } else {
                    tvSample.setVisibility(View.INVISIBLE);
                    swVisibility.setText("Tekst ukryty");
                }
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tvRatingResult.setText("Ocena: " + rating + "/5");

                if (rating >= 4) {
                    tvRatingResult.setTextColor(Color.GREEN);
                } else {
                    tvRatingResult.setTextColor(Color.RED);
                }
            }
        });
    }
}