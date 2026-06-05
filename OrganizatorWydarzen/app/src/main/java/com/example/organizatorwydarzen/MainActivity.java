package com.example.organizatorwydarzen;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> eventList;
    EventAdapter adapter;

    EditText editTextNewEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventList = new ArrayList<>();
        eventList.add("Przykładowe wydarzenie");
        eventList.add("Spotkanie organizacyjne");
        eventList.add("Warsztaty Android");

        EditText editTextNewEvent = findViewById(R.id.editTextNewEvent);
        Button btnAdd = findViewById(R.id.buttonAdd);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEvents);
        Button btnClear = findViewById(R.id.btnClear);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventAdapter(eventList);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.divider);
        divider.setDrawable(drawable);
        recyclerView.addItemDecoration(divider);

        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String draft = prefs.getString("DRAFT_TEXT", "");
        editTextNewEvent.setText(draft);

        btnAdd.setOnClickListener(v -> {
            String text = editTextNewEvent.getText().toString();

            if (text.isEmpty()) {
                Toast.makeText(this, "Wydarzenie nie może być puste!", Toast.LENGTH_SHORT).show();
                return;
            }

            eventList.add(0, text);
            adapter.notifyDataSetChanged();
            editTextNewEvent.setText("");
        });

        btnClear.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Czyszczenie listy");
            builder.setMessage("Czy na pewno chcesz wyczyścić całą listę?");
            builder.setPositiveButton("TAK", (dialog, which) -> {
                eventList.clear();
                adapter.notifyDataSetChanged();
            });
            builder.setNegativeButton("NIE", null);
            builder.show();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String draftText = editTextNewEvent.getText().toString();
        editor.putString("DRAFT_TEXT", draftText);
        editor.apply();
    }
}