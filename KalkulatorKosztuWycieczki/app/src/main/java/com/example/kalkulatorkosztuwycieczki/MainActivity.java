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

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String daysText = days.getText().toString().trim();

                if (daysText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Podaj liczbę dni!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numberDays = Integer.parseInt(daysText);

                int dayCost = 120;
                int totalCost = numberDays * dayCost;

                int selectedId = radioGroupTransport.getCheckedRadioButtonId();

                if (selectedId == R.id.radioBus) {
                    totalCost += 100;
                } else if (selectedId == R.id.radioPlane) {
                    totalCost += 500;
                } else if (selectedId == R.id.radioOwn) {
                    totalCost += 0;
                } else {
                    Toast.makeText(MainActivity.this, "Wybierz rodzaj transportu!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (guide.isChecked()) {
                    totalCost += 150;
                }

                if (breakfast.isChecked()) {
                    totalCost += (30 * numberDays);
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Podsumowanie wycieczki");
                builder.setMessage("Liczba dni: " + numberDays + "\nCałkowity koszt: " + totalCost + " zł");
                builder.setPositiveButton("OK", null);
                builder.show();
            }
        });
    }
}