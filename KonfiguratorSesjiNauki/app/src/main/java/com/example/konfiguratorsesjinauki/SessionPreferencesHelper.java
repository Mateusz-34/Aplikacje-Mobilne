package com.example.konfiguratorsesjinauki;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionPreferencesHelper {

    private static final String PREFS_NAME = "StudySessionPrefs";
    private static final String KEY_REMINDERS = "RemindersEnabled";
    private static final String KEY_SESSION_MINUTES = "SessionMinutes";

    private SharedPreferences sharedPreferences;

    public SessionPreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveSessionMinutes(int minutes) {
        sharedPreferences.edit().putInt(KEY_SESSION_MINUTES, minutes).apply();
    }

    public void saveRemindersEnabled(boolean enabled) {
        sharedPreferences.edit().putBoolean(KEY_REMINDERS, enabled).apply();
    }

    public int loadSessionMinutes() {
        return sharedPreferences.getInt(KEY_SESSION_MINUTES, 45);
    }

    public boolean loadRemindersEnabled() {
        return sharedPreferences.getBoolean(KEY_REMINDERS, true);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}