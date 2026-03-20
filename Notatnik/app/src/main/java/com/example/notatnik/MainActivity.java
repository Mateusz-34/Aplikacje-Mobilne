package com.example.notatnik;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "my_note.txt";

    private EditText editTextNote;
    private Button btnSave;
    private Button btnLoad;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNote = findViewById(R.id.editTextNote);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        btnClear = findViewById(R.id.btnClear);

        btnSave.setOnClickListener(v -> saveNote());
        btnLoad.setOnClickListener(v -> loadNote());

        btnClear.setOnClickListener(v -> {
            editTextNote.setText("");
            saveNote();
            Toast.makeText(this, "Plik i pole tekstowe wyczyszczone", Toast.LENGTH_SHORT).show();
        });

        loadNote();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveNote();
    }

    private void saveNote() {
        String textToSave = editTextNote.getText().toString();
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(textToSave.getBytes());
            Toast.makeText(this, "Zapisano do pliku " + FILE_NAME, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Błąd zapisu!", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadNote() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fis = openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            editTextNote.setText(stringBuilder.toString());
            Toast.makeText(this, "Wczytano z pliku " + FILE_NAME, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Błąd odczytu lub plik nie istnieje!", Toast.LENGTH_SHORT).show();
        }
    }
}