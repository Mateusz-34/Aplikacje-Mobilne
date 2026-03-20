package com.example.logzdarzen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "log.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddLog = findViewById(R.id.btnAddLog);

        btnAddLog.setOnClickListener(v -> {
            try {
                String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                        .format(new Date());

                String wpis = data + "\n";

                FileOutputStream fos = openFileOutput(FILE_NAME, MODE_APPEND);
                fos.write(wpis.getBytes());
                fos.close();

                Toast.makeText(this, "Dodano wpis", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Toast.makeText(this, "Błąd!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        });
    }
}