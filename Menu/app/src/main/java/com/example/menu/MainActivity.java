package com.example.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView infoText;
    private TextView counterText;
    private View mainLayout;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        infoText = findViewById(R.id.infoText);
        counterText = findViewById(R.id.counterText);
        mainLayout = findViewById(R.id.mainLayout);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            counter++;
            counterText.setText(String.valueOf(counter));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(this, "Wybrano Ustawienia", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_info) {
            infoText.setText("Aplikacja stworzona przez: Jana Kowalskiego");
            return true;
        }
        else if (id == R.id.action_favorite) {
            Toast.makeText(this, "Dodano do ulubionych", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_exit) {
            finish();
            return true;
        }
        else if (id == R.id.action_red) {
            mainLayout.setBackgroundColor(Color.RED);
            return true;
        }
        else if (id == R.id.action_green) {
            mainLayout.setBackgroundColor(Color.GREEN);
            return true;
        }
        else if (id == R.id.action_blue) {
            mainLayout.setBackgroundColor(Color.BLUE);
            return true;
        }
        else if (id == R.id.action_reset) {
            counter = 0;
            counterText.setText("0");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}