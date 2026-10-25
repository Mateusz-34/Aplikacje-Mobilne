package com.example.losowaniekostek;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<TextView> kostkiWidoki = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        Button buttonLosuj = findViewById(R.id.buttonLosuj);
        TextView textViewWyniki = findViewById(R.id.textViewWyniki);
        GridLayout gridKostki = findViewById(R.id.gridKostki);

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

                for (int i = 0; i < kostkiWidoki.size(); i++) {
                    TextView tv = kostkiWidoki.get(i);
                    gridKostki.removeView(tv);
                }
                kostkiWidoki.clear();

                gridKostki.setColumnCount(5);

                List<Integer> wyniki = new ArrayList<>();
                Random random = new Random();

                for (int i = 0; i < liczbaKostek; i++) {
                    int los = random.nextInt(6) + 1;
                    wyniki.add(los);

                    TextView kostka = new TextView(MainActivity.this);
                    kostka.setText(String.valueOf(los));
                    kostka.setTextSize(18);
                    kostka.setPadding(16, 16, 16, 16);

                    gridKostki.addView(kostka);
                    kostkiWidoki.add(kostka);
                }

                String listaWynikow = wyniki.toString().replace("[", "").replace("]", "");
                textViewWyniki.setText(listaWynikow);
            }
        });
    }
}