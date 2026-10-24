package com.example.losowaniekostek;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        Button buttonLosuj = findViewById(R.id.buttonLosuj);
        TextView textViewWyniki = findViewById(R.id.textViewWyniki);

        buttonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editTextLiczbaKostek.getText().toString();

                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wpisz liczbe kostek!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int liczbaKostek = Integer.parseInt(input);
                if (liczbaKostek < 1 || liczbaKostek > 100) {
                    Toast.makeText(MainActivity.this, "Liczba kostek musi być z przedzialu od 1 do 100", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Podana liczba kostek to: " + liczbaKostek, Toast.LENGTH_SHORT).show();
                }

                List<Integer> wyniki = new ArrayList<>();
                Random random = new Random();

                for (int i = 0; i < liczbaKostek; i++) {
                    int los = random.nextInt(6) + 1;
                    wyniki.add(los);
                }

                textViewWyniki.setText("Wyniki losowania: " + wyniki.toString());
            }
        });
    }
}