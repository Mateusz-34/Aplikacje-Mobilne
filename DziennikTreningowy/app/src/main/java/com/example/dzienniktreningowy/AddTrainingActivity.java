package com.example.dzienniktreningowy;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTrainingActivity extends AppCompatActivity {

    private EditText editName, editReps, editDuration;
    private Button buttonSave;
    private TrainingDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_training);

        dbHelper = new TrainingDbHelper(this);

        editName = findViewById(R.id.editExerciseName);
        editReps = findViewById(R.id.editRepsCount);
        editDuration = findViewById(R.id.editDurationCount);
        buttonSave = findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTraining();
            }
        });
    }

    private void saveTraining() {
        String name = editName.getText().toString().trim();
        String repsString = editReps.getText().toString().trim();
        String durationString = editDuration.getText().toString().trim();

        if (name.isEmpty() || repsString.isEmpty() || durationString.isEmpty()) {
            Toast.makeText(this, "Uzupełnij wszystkie pola", Toast.LENGTH_SHORT).show();
            return;
        }

        int reps, duration;
        try {
            reps = Integer.parseInt(repsString);
            duration = Integer.parseInt(durationString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Niepoprawne liczby", Toast.LENGTH_SHORT).show();
            return;
        }

        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TrainingDbHelper.COLUMN_NAME, name);
        values.put(TrainingDbHelper.COLUMN_REPS, reps);
        values.put(TrainingDbHelper.COLUMN_DURATION, duration);
        values.put(TrainingDbHelper.COLUMN_DATE, currentDate);

        long newRowId = db.insert(TrainingDbHelper.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Błąd zapisu", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Trening zapisany", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}