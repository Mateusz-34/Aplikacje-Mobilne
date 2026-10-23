package com.example.rejestracjakonta2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextSurname = findViewById(R.id.editTextSurname);
        EditText editTextEmail = findViewById(R.id.editTextEmail);
        EditText editTextPassword = findViewById(R.id.editTextPassword);

        Button registerButton = findViewById(R.id.buttonRegister);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String surname = editTextSurname.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString();

                if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!email.contains("@") || !email.contains(".")){
                    Toast.makeText(MainActivity.this, "Podaj poprawny adres email", Toast.LENGTH_SHORT).show();
                    return;
                }

                java.util.List<String> braki = new java.util.ArrayList<>();

                if (password.length() < 8) {
                    braki.add("co najmniej 8 znaków");
                }
                if (!password.matches(".*[A-Z].*")) {
                    braki.add("Dużą literę");
                }
                if (!password.matches(".*[a-z].*")) {
                    braki.add("Małą literę");
                }
                if (!password.matches(".*[^a-zA-Z0-9].*")) {
                    braki.add("znak specjalny");
                }

                if (!braki.isEmpty()) {
                    String wiadomosc = "Hasło musi mieć " + android.text.TextUtils.join(", ", braki);
                    Toast.makeText(MainActivity.this, wiadomosc, Toast.LENGTH_SHORT).show();
                    return;
                }


                Toast.makeText(MainActivity.this, "Dane są poprawne", Toast.LENGTH_SHORT).show();
            }
        });
    }
}