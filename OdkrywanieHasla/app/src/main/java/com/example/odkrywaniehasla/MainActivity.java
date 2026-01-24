package com.example.odkrywaniehasla;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editPassword = findViewById(R.id.editPassword);
        Switch switchShow = findViewById(R.id.switchShow);

        switchShow.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editPassword.setTransformationMethod(
                        HideReturnsTransformationMethod.getInstance()
                );
            } else {
                editPassword.setTransformationMethod(
                        PasswordTransformationMethod.getInstance()
                );
            }
        });
    }
}