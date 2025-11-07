package com.example.zadaniedialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int licznik = 0;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button1);

        button.setOnClickListener(v -> showDialog());
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Czy na pewno chcesz usunąć dane?");
        builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                licznik++;
                updateTextView();
                Toast.makeText(MainActivity.this, "Dane zostały usunięte", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Zamknięcie dialogu bez zmian
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void updateTextView() {
        String text;
        if (licznik == 1) {
            text = "Dane usunięto: 1 raz";
        } else {
            text = "Dane usunięto: " + licznik + " razy";
        }
        textView.setText(text);
    }
}
