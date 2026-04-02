package com.example.materialnotesapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText titleInput, contentInput;
    private TextInputLayout titleLayout;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleLayout = findViewById(R.id.titleLayout);
        titleInput = findViewById(R.id.titleInput);
        contentInput = findViewById(R.id.contentInput);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleInput.getText().toString().trim();
                String content = contentInput.getText().toString().trim();

                if (title.isEmpty()) {
                    titleLayout.setError("Tytuł nie może być pusty!");
                    return;
                } else {
                    titleLayout.setError(null);
                }

                Snackbar.make(view, "Zapisano: " + title, Snackbar.LENGTH_LONG)
                        .setAction("COFNIJ", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                titleInput.setText("");
                                contentInput.setText("");
                            }
                        })
                        .show();
            }
        });
    }
}