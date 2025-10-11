package com.example.kalkulatorbmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput;
    private EditText heightInput;
    private Button calculateButton;
    private TextView resultText;
    private TextView interpretationText;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateButton = findViewById(R.id.calculateButton);
        resultText = findViewById(R.id.resultText);
        interpretationText = findViewById(R.id.interpretationText);
        clear = findViewById(R.id.clear);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBmi();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightInput.setText("");
                heightInput.setText("");
                resultText.setText("");
                interpretationText.setText("");
            }
        });

    }

    private void calculateBmi() {
        String weightStr = weightInput.getText().toString();
        String heightStr = heightInput.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Błąd walidacji");
            builder.setMessage(R.string.error_empty_fields);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        float weight = Float.parseFloat(weightStr);
        float heightCm = Float.parseFloat(heightStr);

        if (weight <= 0 || heightCm <= 0) {
            Toast.makeText(this, R.string.error_zero_values, Toast.LENGTH_SHORT).show();
            return;
        }

        float heightM = heightCm / 100.0f;
        float bmi = weight / (heightM * heightM);

        String bmiResultString = String.format("%.2f", bmi);
        String resultPrefix = getResources().getString(R.string.bmi_result_prefix);
        resultText.setText(resultPrefix + " " + bmiResultString);

        if(bmi < 18.5){
            interpretationText.setText(getResources().getString(R.string.bmi_underweight));
            interpretationText.setTextColor(getResources().getColor(R.color.status_warning1, null));
        } else if (bmi > 18.5 && bmi < 24.9) {
            interpretationText.setText(getResources().getString(R.string.bmi_normal));
            interpretationText.setTextColor(getResources().getColor(R.color.status_good, null));
        } else if (bmi > 25.0 && bmi < 29.9) {
            interpretationText.setText(getResources().getString(R.string.bmi_overweight));
            interpretationText.setTextColor(getResources().getColor(R.color.status_warning2, null));
        } else if (bmi >= 30.0) {
            interpretationText.setText(getResources().getString(R.string.bmi_obesity));
            interpretationText.setTextColor(getResources().getColor(R.color.status_bad, null));
        }
    }
}