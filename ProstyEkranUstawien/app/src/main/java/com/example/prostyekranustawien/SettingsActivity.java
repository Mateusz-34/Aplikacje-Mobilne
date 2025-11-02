package com.example.prostyekranustawien;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "user_settings";
    private static final String KEY_NAME = "user_name";
    private static final String KEY_NOTIFICATIONS = "notifications_enabled";

    private EditText nameInput;
    private Switch notificationsSwitch;
    private Button saveButton;

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        nameInput = findViewById(R.id.nameInput);
        notificationsSwitch = findViewById(R.id.notificationsSwitch);
        saveButton = findViewById(R.id.saveButton);

        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        String savedName = prefs.getString(KEY_NAME, "");
        boolean notificationsEnabled = prefs.getBoolean(KEY_NOTIFICATIONS, false);

        nameInput.setText(savedName);
        notificationsSwitch.setChecked(notificationsEnabled);

        saveButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(KEY_NAME, nameInput.getText().toString());
            editor.putBoolean(KEY_NOTIFICATIONS, notificationsSwitch.isChecked());
            editor.apply();

            Toast.makeText(this, "Ustawienia zapisane", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
