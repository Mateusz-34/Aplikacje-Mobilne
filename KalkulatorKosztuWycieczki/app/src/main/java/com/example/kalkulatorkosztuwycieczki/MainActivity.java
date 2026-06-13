package com.example.kalkulatorkosztuwycieczki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText days = findViewById(R.id.editTextDays);
        RadioGroup radioGroupTransport = findViewById(R.id.radioGroupTransport);
        CheckBox breakfast = findViewById(R.id.checkBreakfast);
        CheckBox guide = findViewById(R.id.checkGuide);
        Button calculate = findViewById(R.id.calculate);
        Button reset = findViewById(R.id.reset);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String daysText = days.getText().toString().trim();

                if (daysText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Podaj liczbę dni!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numberDays = Integer.parseInt(daysText);

                if (numberDays < 1 || numberDays > 30) {
                    Toast.makeText(MainActivity.this, "Liczba dni musi wynosić od 1 do 30!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedId = radioGroupTransport.getCheckedRadioButtonId();

                if (selectedId == -1) {
                    Toast.makeText(MainActivity.this, "Wybierz rodzaj transportu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int dayCost = 120;
                int baseCost = numberDays * dayCost;
                int totalCost = baseCost;

                StringBuilder messageBuilder = new StringBuilder();
                messageBuilder.append("Liczba dni: ").append(numberDays).append("\n");
                messageBuilder.append("Koszt bazowy: ").append(baseCost).append(" zł\n");

                if (selectedId == R.id.radioBus) {
                    totalCost += 100;
                    messageBuilder.append("Transport: Autokar (+100 zł)\n");
                } else if (selectedId == R.id.radioPlane) {
                    totalCost += 500;
                    messageBuilder.append("Transport: Samolot (+500 zł)\n");
                } else if (selectedId == R.id.radioOwn) {
                    messageBuilder.append("Transport: Dojazd własny (+0 zł)\n");
                }

                if (guide.isChecked()) {
                    totalCost += 150;
                    messageBuilder.append("Przewodnik: (+150 zł)\n");
                }

                if (breakfast.isChecked()) {
                    int breakfastCost = 30 * numberDays;
                    totalCost += breakfastCost;
                    messageBuilder.append("Śniadania: (+").append(breakfastCost).append(" zł)\n");
                }

                messageBuilder.append("──────────────────\n");
                messageBuilder.append("ŁĄCZNIE: ").append(totalCost).append(" zł");

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Podsumowanie wycieczki");
                builder.setMessage(messageBuilder.toString());
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                days.setText("");
                radioGroupTransport.clearCheck();
                breakfast.setChecked(false);
                guide.setChecked(false);
                Toast.makeText(MainActivity.this, "Formularz został wyczyszczony", Toast.LENGTH_SHORT).show();
            }
        });
    }
}