package com.example.intentyaktywnosci;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        EditText Name = findViewById(R.id.Name);
        EditText Surname = findViewById(R.id.Surname);
        EditText Email = findViewById(R.id.Email);

        String messageName = getIntent().getStringExtra("messageName");
        String messageSurname = getIntent().getStringExtra("messageSurname");
        String messageEmail = getIntent().getStringExtra("messageEmail");

        Name.setText(messageName);
        Surname.setText(messageSurname);
        Email.setText(messageEmail);

        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(v-> {
            String messagename = Name.getText().toString();
            String messagesurname = Surname.getText().toString();
            String messageemail = Email.getText().toString();

            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            intent.putExtra("name",messagename);
            intent.putExtra("surname",messagesurname);
            intent.putExtra("email",messageemail);

            startActivity(intent);
            finish();
        });

        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(v -> finish());
    }
}