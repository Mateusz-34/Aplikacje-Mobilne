package com.example.konfiguratorsesjinauki;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_HISTORY = "SessionHistory";
    private static final String KEY_ADAPTIVE_BREAK = "AdaptiveBreak";

    private Switch switchReminders;
    private Switch switchAdaptiveBreak;
    private SeekBar seekBarSession;
    private TextView tvSessionValue;
    private TextView tvSummary;

    private SessionPreferencesHelper prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchReminders = findViewById(R.id.switchReminders);
        switchAdaptiveBreak = findViewById(R.id.switchAdaptiveBreak);
        seekBarSession = findViewById(R.id.seekBarSession);
        tvSessionValue = findViewById(R.id.tvSessionValue);
        tvSummary = findViewById(R.id.tvSummary);

        prefs = new SessionPreferencesHelper(this);

        loadSettings();
        setupListeners();
    }

    private void setupListeners() {
        switchReminders.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.saveRemindersEnabled(isChecked);
            updateSummary();
        });

        switchAdaptiveBreak.setOnCheckedChangeListener((buttonView, isChecked) -> {
            prefs.getSharedPreferences().edit().putBoolean(KEY_ADAPTIVE_BREAK, isChecked).apply();
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

                prefs.saveSessionMinutes(minutes);
                updateHistory(minutes);

                if (minutes > 70 && !switchReminders.isChecked()) {
                    Toast.makeText(MainActivity.this, "UWAGA! Włączono przypomnienia.", Toast.LENGTH_SHORT).show();
                    switchReminders.setChecked(true);
                }
            }
        });
    }

    private void loadSettings() {
        boolean remindersEnabled = prefs.loadRemindersEnabled();
        boolean adaptiveBreak = prefs.getSharedPreferences().getBoolean(KEY_ADAPTIVE_BREAK, false);
        int sessionMinutes = prefs.loadSessionMinutes();

        switchReminders.setChecked(remindersEnabled);
        switchAdaptiveBreak.setChecked(adaptiveBreak);
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
        String history = prefs.getSharedPreferences().getString(KEY_HISTORY, "");

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

        prefs.getSharedPreferences().edit().putString(KEY_HISTORY, history).apply();
    }

    private int getAverage() {
        String history = prefs.getSharedPreferences().getString(KEY_HISTORY, "");
        if (history.isEmpty()) return 0;

        String[] parts = history.split(",");
        int sum = 0;

        for (String p : parts) {
            sum += Integer.parseInt(p);
        }

        return sum / parts.length;
    }

    private int getAdaptiveBreak(int minutes) {
        return (int) Math.ceil(minutes * 0.2);
    }

    private void updateSessionLabel(int minutes) {
        tvSessionValue.setText(String.format(Locale.getDefault(), "%d min", minutes));
    }

    private void updateSummary() {
        boolean reminders = switchReminders.isChecked();
        boolean adaptive = switchAdaptiveBreak.isChecked();
        int minutes = progressToMinutes(seekBarSession.getProgress());
        int average = getAverage();

        String history = prefs.getSharedPreferences().getString(KEY_HISTORY, "-");
        String reminderText = reminders ? "włączone" : "wyłączone";

        String summary = "Plan sesji:\n"
                + "• Czas: " + minutes + " min\n"
                + "• Przypomnienia: " + reminderText + "\n"
                + "• Historia: " + history + "\n"
                + "• Średnia: " + average;

        if (adaptive) {
            int breakTime = getAdaptiveBreak(minutes);
            summary += "\n• Rekomendowana przerwa: " + breakTime + " min";
        }

        tvSummary.setText(summary);

        setLoadColor(minutes);
    }

    private void setLoadColor(int minutes) {
        if (minutes <= 35) {
            tvSummary.setTextColor(Color.GREEN);
            return;
        }

        if (minutes <= 60) {
            tvSummary.setTextColor(Color.rgb(255, 165, 0));
            return;
        }

        tvSummary.setTextColor(Color.RED);
    }
}