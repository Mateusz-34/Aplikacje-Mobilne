package com.example.odgosyzwierzt;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgCat = findViewById(R.id.imgCat);
        ImageView imgDog = findViewById(R.id.imgDog);
        ImageView imgCow = findViewById(R.id.imgCow);

        imgCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(R.raw.sample_cat_sound);
            }
        });

        imgDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(R.raw.sample_dog_sound);
            }
        });

        imgCow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(R.raw.sample_cow_sound);
            }
        });
    }

    void playSound(int sound) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, sound);
        mediaPlayer.start();
    }
}