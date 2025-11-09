package com.example.prostaksiazkatelefoniczna;

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
    private EditText nameInput;
    private EditText phoneInput;
    private Button saveButton;
    private TextView contactsDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        nameInput = findViewById(R.id.nameInput);
        phoneInput = findViewById(R.id.phoneInput);
        saveButton = findViewById(R.id.saveButton);
        contactsDisplay = findViewById(R.id.contactsDisplay);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });

        loadContacts();
    }

    private void addContact() {
        String name = nameInput.getText().toString().trim();
        String phone = phoneInput.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_PHONE, phone);

        db.insert(DatabaseHelper.TABLE_CONTACTS, null, values);
        db.close();

        nameInput.setText("");
        phoneInput.setText("");

        loadContacts();
    }

    private void loadContacts() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_PHONE};
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_CONTACTS,
                projection,
                null, null, null, null, null
        );

        StringBuilder contactsText = new StringBuilder();

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE));
            contactsText.append(name).append(" - ").append(phone).append("\n");
        }

        cursor.close();
        db.close();

        if (contactsText.length() == 0) {
            contactsDisplay.setText("Brak kontaktów");
        } else {
            contactsDisplay.setText(contactsText.toString());
        }
    }
}
