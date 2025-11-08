package com.example.bazydane;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText noteTitleInput, noteInput;
    private Button saveButton;
    private TextView notesDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        noteTitleInput = findViewById(R.id.noteTitleInput);
        noteInput = findViewById(R.id.noteInput);
        saveButton = findViewById(R.id.saveButton);
        notesDisplay = findViewById(R.id.notesDisplay);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
        loadNotes();
    }

    private void addNote() {
        String title = noteTitleInput.getText().toString();
        String note = noteInput.getText().toString();

        if (title.isEmpty() || note.isEmpty()) {
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE, title);
        values.put(DatabaseHelper.COLUMN_NOTE, note);

        db.insert(DatabaseHelper.TABLE_NOTES, null, values);
        db.close();

        noteTitleInput.setText("");
        noteInput.setText("");

        loadNotes();
    }

    private void loadNotes() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_TITLE, DatabaseHelper.COLUMN_NOTE};

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NOTES,
                projection,
                null, null, null, null, null
        );

        StringBuilder notesText  = new StringBuilder();
        while (cursor.moveToNext()) {
            String noteTitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TITLE));
            notesText.append("Tytuł notatki: ").append(noteTitle).append("\n");

            String noteText = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOTE));
            notesText.append("Treść notatki: ").append(noteText).append("\n\n");
        }
        cursor.close();
        db.close();

        notesDisplay.setText(notesText.toString());
    }
}