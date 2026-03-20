package com.example.listaplikow;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewFiles = findViewById(R.id.textViewFiles);

        String[] files = fileList();
        StringBuilder sb = new StringBuilder();

        for (String filename : files) {
            sb.append(filename).append("\n");
        }

        textViewFiles.setText(sb.toString());
    }
}