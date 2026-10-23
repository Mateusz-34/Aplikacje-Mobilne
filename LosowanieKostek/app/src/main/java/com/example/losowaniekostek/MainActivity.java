package com.example.losowaniekostek;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonLosuj = findViewById(R.id.buttonLosuj);

        buttonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sprawdzLiczbe();
            }
        });
    }

    public void sprawdzLiczbe(){
        EditText editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        String input = editTextLiczbaKostek.getText().toString();

        if (input.isEmpty()) {
            Toast.makeText(this, "Wpisz liczbe kostek", Toast.LENGTH_SHORT).show();
            return;
        }

        int liczba = Integer.parseInt(input);
        if (liczba < 1 || liczba > 100) {
            Toast.makeText(this, "Liczba kostek musi być z przedzialu od 1 do 100", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Podana liczba kostek to: " + liczba, Toast.LENGTH_SHORT).show();
        }
    }
}