package com.example.prostyekranustawien;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "user_settings";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_NOTIFICATIONS = "notifications_enabled";

    private TextView greetingText;
    private Button settingsButton;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        greetingText = findViewById(R.id.greetingText);
        settingsButton = findViewById(R.id.settingsButton);

        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        loadUserSettings();

        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserSettings();
    }

    private void loadUserSettings() {
        String name = prefs.getString(KEY_NAME, "");
        boolean notifications = prefs.getBoolean(KEY_NOTIFICATIONS, false);

        if (!name.isEmpty()) {
            greetingText.setText("Witaj, " + name + "!");
        } else {
            greetingText.setText("Witaj, gościu!");
        }
    }
}
