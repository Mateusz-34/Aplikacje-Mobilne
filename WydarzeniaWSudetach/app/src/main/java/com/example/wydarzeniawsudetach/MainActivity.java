package com.example.wydarzeniawsudetach;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView likeCounter, textMessage;
    private Button buttonLike, buttonDisLike, buttonSave, buttonShow;
    private EditText editTextEmail, editTextPassword1, editTextPassword2;
    private int likeCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        likeCounter = findViewById(R.id.likeCounter);
        buttonLike = findViewById(R.id.buttonLike);
        buttonDisLike = findViewById(R.id.buttonDisLike);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword1 = findViewById(R.id.editTextPassword1);
        editTextPassword2 = findViewById(R.id.editTextPassword2);
        buttonSave = findViewById(R.id.buttonSave);
        buttonShow = findViewById(R.id.buttonShow);
        textMessage = findViewById(R.id.textMessage);

        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeCount++;
                updateLikeCounter();
            }
        });

        buttonDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (likeCount > 0) {
                    likeCount--;
                }
                updateLikeCounter();
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString().trim();
                String password1 = editTextPassword1.getText().toString().trim();
                String password2 = editTextPassword2.getText().toString().trim();

                if (!email.contains("@")) {
                    textMessage.setText("Nieprawidłowy adres email");
                } else if (!password1.equals(password2)) {
                    textMessage.setText("Hasła się różnią");
                } else {
                    textMessage.setText("Zarejestrowano: " + email);
                }

            }
        });

        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();

                textMessage.setText("Ostatnio zalogowany uczestnik to: " + email);
            }
        });

    }

    private void updateLikeCounter() {
        likeCounter.setText(likeCount + " polubień");
    }
}
