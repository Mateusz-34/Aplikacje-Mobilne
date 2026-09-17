package com.example.rejestracja;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword1;
    private EditText editTextPassword2;
    private TextView textViewMessage;

    private Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword1 = findViewById(R.id.editTextPassword1);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        textViewMessage = findViewById(R.id.textViewMessage);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString().trim();
                String password1 = editTextPassword1.getText().toString().trim();
                String password2 = editTextPassword2.getText().toString().trim();


                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    textViewMessage.setText("Nieprawidłowy adres email");
                } else if(!password1.equals(password2)) {
                    textViewMessage.setText("Hasła się różnią");
                } else {
                    textViewMessage.setText("Witaj " + email);
                }
            }
        });
    }
}