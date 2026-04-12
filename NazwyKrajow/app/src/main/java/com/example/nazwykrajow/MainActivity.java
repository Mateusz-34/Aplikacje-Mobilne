package com.example.nazwykrajow;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteCountry;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteCountry = findViewById(R.id.autoCompleteCountry);
        btnConfirm = findViewById(R.id.btnConfirm);

        String[] countries = {
            "Polska" , "Niemcy" , "Czechy" , "Słowacja" , "Francja" , "Hiszpania" , "Włochy" , "Wielka Brytania"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        autoCompleteCountry.setAdapter(adapter);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCountry = autoCompleteCountry.getText().toString();
                Toast.makeText(MainActivity.this,
                        "Wybrano: " + selectedCountry,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}