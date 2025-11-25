package com.example.przelicznikwalut;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Spinner fromCurrencySpinner;
    private Spinner toCurrencySpinner;
    private EditText amountInput;
    private TextView resultText;
    private SharedPreferences prefs;
    private ImageView fromCurrencyImage;
    private ImageView toCurrencyImage;
    private final String[] currencies = {"PLN", "EUR", "USD", "GBP"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner);
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner);
        amountInput = findViewById(R.id.amountInput);
        resultText = findViewById(R.id.resultText);
        fromCurrencyImage = findViewById(R.id.fromCurrencyImage);
        toCurrencyImage = findViewById(R.id.toCurrencyImage);

        prefs = getSharedPreferences("waluty", MODE_PRIVATE);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currencies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromCurrencySpinner.setAdapter(adapter);
        toCurrencySpinner.setAdapter(adapter);

        int savedFrom = prefs.getInt("fromPos", 0);
        int savedTo = prefs.getInt("toPos", 0);
        fromCurrencySpinner.setSelection(savedFrom);
        toCurrencySpinner.setSelection(savedTo);

        updateFlag(fromCurrencyImage, currencies[savedFrom]);
        updateFlag(toCurrencyImage, currencies[savedTo]);

        fromCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateFlag(fromCurrencyImage, currencies[position]);
                convertCurrency();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        toCurrencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateFlag(toCurrencyImage, currencies[position]);
                convertCurrency();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        amountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { convertCurrency(); }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        Button convertButton = findViewById(R.id.convertButton);
        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { convertCurrency(); }
        });
    }

    private void updateFlag(ImageView imageView, String currency) {
        switch (currency) {
            case "PLN": imageView.setImageResource(R.drawable.flag_pln); break;
            case "EUR": imageView.setImageResource(R.drawable.flag_eur); break;
            case "USD": imageView.setImageResource(R.drawable.flag_usd); break;
            case "GBP": imageView.setImageResource(R.drawable.flag_gbp); break;
        }
    }

    private void convertCurrency() {
        String amountStr = amountInput.getText().toString();
        if (amountStr.isEmpty()) { resultText.setText(""); return; }

        double amount;
        try { amount = Double.parseDouble(amountStr); }
        catch (NumberFormatException e) { resultText.setText(""); return; }

        String fromCurrency = fromCurrencySpinner.getSelectedItem().toString();
        String toCurrency = toCurrencySpinner.getSelectedItem().toString();

        HashMap<String, Double> rates = new HashMap<>();
        rates.put("PLN", 1.0);
        rates.put("EUR", 4.30);
        rates.put("USD", 4.00);
        rates.put("GBP", 5.00);

        double inPln = amount * rates.get(fromCurrency);
        double result = inPln / rates.get(toCurrency);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("fromPos", fromCurrencySpinner.getSelectedItemPosition());
        editor.putInt("toPos", toCurrencySpinner.getSelectedItemPosition());
        editor.apply();

        resultText.setText(String.format("%.2f %s", result, toCurrency));
    }
}