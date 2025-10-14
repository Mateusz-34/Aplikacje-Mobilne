package com.example.notifications;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static int ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationHelper.createNotificationChannels(this);


        Button buttonLongText = findViewById(R.id.buttonLongText);
        buttonLongText.setOnClickListener(v -> {
            NotificationHelper.sendNotification(ID, NotificationHelper.CHANNEL_ID_BIGTEXT, this, "Powiadomienie z dlugim tekstem", "To jest powiadomienie z dlugim tekstem. To jest powiadomienie z dlugim tekstem. To jest powiadomienie z dlugim tekstem. To jest powiadomienie z dlugim tekstem. To jest powiadomienie z dlugim tekstem. To jest powiadomienie z dlugim tekstem. To jest powiadomienie z dlugim tekstem", 1 ,0);
            ID++;
        });

        Button buttonPicture = findViewById(R.id.buttonPicture);
        buttonPicture.setOnClickListener(v -> {
            NotificationHelper.sendNotification(ID, NotificationHelper.CHANNEL_ID_PICTURE, this, "Powiadomienie z obrazem", "To jest powiadomienie z obrazem", 2 ,R.drawable.kosc);
            ID++;
        });

        Button buttonLongLongText = findViewById(R.id.buttonLongLongText);
        buttonLongLongText.setOnClickListener(v -> {
            NotificationHelper.sendNotification(ID, NotificationHelper.CHANNEL_ID_INBOX, this, "Powiadomienie z wieloma liniami tekstu", "To jest powiadomienie z wieloma liniami tekstu", 3 ,0);
            ID++;
        });
    }
}