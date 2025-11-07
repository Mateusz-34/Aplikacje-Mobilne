package com.example.osobistepowitanie;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextImie;
    private Button buttonPowitanie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationHelper.createNotificationChannels(this);

        editTextImie = findViewById(R.id.editTextImie);
        buttonPowitanie = findViewById(R.id.buttonPowitanie);

        buttonPowitanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextImie.getText().toString().trim();

                if (name.isEmpty()) {
                    showAlertDialog();
                } else {
                    showAlertDialog2();
                }
            }
        });

    }

    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Błąd");
        builder.setMessage("Proszę wpisać swoje imię!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.create().show();
    }

    private void showAlertDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Potwierdzenie");
        String name = editTextImie.getText().toString().trim();
        builder.setMessage("Cześć "+ name +"! Czy chcesz otrzymać powiadomienie powitalne?");
        builder.setPositiveButton("Tak, poproszę", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendSimpleNotification(name);
            }
        });
        builder.setNegativeButton("Nie, dziękuję", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this , "Rozumiem. Nie wysyłam powiadomienia.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();

    }
    private void sendSimpleNotification(String name) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID_DEFAULT)
                .setSmallIcon(R.mipmap.ic_launcher) // Domyślna ikona aplikacji
                .setContentTitle("Witaj!")
                .setContentText("Miło Cię widzieć, " + name + "!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        Toast.makeText(this, "Powiadomienie zostało wysłane!", Toast.LENGTH_SHORT).show();
    }

}