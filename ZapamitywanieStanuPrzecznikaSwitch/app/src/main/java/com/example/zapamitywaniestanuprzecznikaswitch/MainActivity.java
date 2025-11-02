package com.example.zapamitywaniestanuprzecznikaswitch;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "my_prefs";
    private static final String KEY_SWITCH_STATE = "switch_state";

    private Switch switchSave;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchSave = findViewById(R.id.switch_save);
        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        boolean savedState = prefs.getBoolean(KEY_SWITCH_STATE, false);
        switchSave.setChecked(savedState);

        switchSave.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                prefs.edit().putBoolean(KEY_SWITCH_STATE, isChecked).apply();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        prefs.edit().putBoolean(KEY_SWITCH_STATE, switchSave.isChecked()).commit();
    }
}
