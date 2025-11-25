package com.example.przelicznikwalut;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;
    private Button convertButton;
    private EditText amountInput;
    private TextView resultText;
    private SharedPreferences prefs;

    private final String[] currencies = {"PLN", "EUR", "USD", "GBP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("waluty", MODE_PRIVATE);

        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        convertButton = findViewById(R.id.convertButton);
        amountInput = findViewById(R.id.amountInput);
        resultText = findViewById(R.id.resultText);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);

        int savedFrom = prefs.getInt("fromPos", 0);
        int savedTo = prefs.getInt("toPos", 0);
        fromCurrencySpinner.setSelection(savedFrom);
        toCurrencySpinner.setSelection(savedTo);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertCurrency();
            }
        });
    }

    private void convertCurrency() {
        String amountStr = amountInput.getText().toString();

        if (amountStr.isEmpty()) {
            Toast.makeText(this, "Wprowadź kwotę", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);

        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();

        double EUR = 4.30;
        double USD = 4.00;
        double GBP = 5.00;

        double inPln = 0;
        double result = 0;

        if (fromCurrency.equals("PLN")) inPln = amount;
        else if (fromCurrency.equals("EUR")) inPln = amount * EUR;
        else if (fromCurrency.equals("USD")) inPln = amount * USD;
        else if (fromCurrency.equals("GBP")) inPln = amount * GBP;

        if (toCurrency.equals("PLN")) result = inPln;
        else if (toCurrency.equals("EUR")) result = inPln / EUR;
        else if (toCurrency.equals("USD")) result = inPln / USD;
        else if (toCurrency.equals("GBP")) result = inPln / GBP;

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("fromPos", fromCurrencySpinner.getSelectedItemPosition());
        editor.putInt("toPos", toCurrencySpinner.getSelectedItemPosition());
        editor.apply();

        resultText.setText(String.format("%.2f %s", result, toCurrency));
    }
}