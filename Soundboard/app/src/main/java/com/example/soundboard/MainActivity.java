package com.example.soundboard;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = findViewById(R.id.btnPlay);
        Button btnPause = findViewById(R.id.btnPause);
        Button btnStop = findViewById(R.id.btnStop);
        VideoView videoView = findViewById(R.id.videoView);

        mediaPlayer = MediaPlayer.create(this, R.raw.sample_music);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "Odtwarzanie zakończone", Toast.LENGTH_SHORT).show();
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.sample_music);
            }
        });

        String path = "android.resource://" + getPackageName() + "/" + R.raw.sample_video;
        videoView.setVideoURI(Uri.parse(path));

        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        controller.setAnchorView(videoView);

        videoView.start();
    }
}