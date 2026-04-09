package com.example.konfiguratorsesjinauki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "StudySessionPrefs";
    private static final String KEY_REMINDERS = "RemindersEnabled";
    private static final String KEY_SESSION_MINUTES = "SessionMinutes";
    private static final String KEY_HISTORY = "SessionHistory";

    private static final int DEFAULT_SESSION_MINUTES = 45;

    private Switch switchReminders;
    private SeekBar seekBarSession;
    private TextView tvSessionValue;
    private TextView tvSummary;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchReminders = findViewById(R.id.switchReminders);
        seekBarSession = findViewById(R.id.seekBarSession);
        tvSessionValue = findViewById(R.id.tvSessionValue);
        tvSummary = findViewById(R.id.tvSummary);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        loadSettings();
        setupListeners();
    }

    private void setupListeners() {
        switchReminders.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveBoolean(KEY_REMINDERS, isChecked);
            updateSummary();
        });

        seekBarSession.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = progressToMinutes(progress);
                updateSessionLabel(minutes);
                updateSummary();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int minutes = progressToMinutes(seekBar.getProgress());

                saveInt(KEY_SESSION_MINUTES, minutes);
                updateHistory(minutes);

                if (minutes > 70 && !switchReminders.isChecked()) {
                    Toast.makeText(MainActivity.this, "UWAGA! Włączono przypomnienia.", Toast.LENGTH_SHORT).show();
                    switchReminders.setChecked(true);
                }
            }
        });
    }

    private void loadSettings() {
        boolean remindersEnabled = sharedPreferences.getBoolean(KEY_REMINDERS, true);
        int sessionMinutes = sharedPreferences.getInt(KEY_SESSION_MINUTES, DEFAULT_SESSION_MINUTES);

        switchReminders.setChecked(remindersEnabled);
        seekBarSession.setProgress(minutesToProgress(sessionMinutes));

        updateSessionLabel(sessionMinutes);
        updateSummary();
    }

    private int progressToMinutes(int progress) {
        return 15 + (progress * 5);
    }

    private int minutesToProgress(int minutes) {
        int clamped = Math.max(15, Math.min(90, minutes));
        return (clamped - 15) / 5;
    }

    private void updateHistory(int minutes) {
        String history = sharedPreferences.getString(KEY_HISTORY, "");

        if (!history.isEmpty()) {
            history += "," + minutes;
        } else {
            history = String.valueOf(minutes);
        }

        String[] parts = history.split(",");

        if (parts.length > 5) {
            history = "";
            for (int i = parts.length - 5; i < parts.length; i++) {
                history += parts[i];
                if (i < parts.length - 1) history += ",";
            }
        }

        sharedPreferences.edit().putString(KEY_HISTORY, history).apply();
    }

    private int getAverage() {
        String history = sharedPreferences.getString(KEY_HISTORY, "");
        if (history.isEmpty()) return 0;

        String[] parts = history.split(",");
        int sum = 0;

        for (String p : parts) {
            sum += Integer.parseInt(p);
        }

        return sum / parts.length;
    }

    private void updateSessionLabel(int minutes) {
        tvSessionValue.setText(String.format(Locale.getDefault(), "%d min", minutes));
    }

    private void updateSummary() {
        boolean reminders = switchReminders.isChecked();
        int minutes = progressToMinutes(seekBarSession.getProgress());
        int average = getAverage();

        String history = sharedPreferences.getString(KEY_HISTORY, "-");

        String reminderText = reminders ? "włączone" : "wyłączone";

        String summary = "Plan sesji:\n"
                + "• Czas: " + minutes + " min\n"
                + "• Przypomnienia: " + reminderText + "\n"
                + "• Historia: " + history + "\n"
                + "• Średnia: " + average;

        tvSummary.setText(summary);
    }

    private void saveBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    private void saveInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }
}