package com.example.organizatorwydarzen;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> eventList;
    EventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventList = new ArrayList<>();
        eventList.add("Przykładowe wydarzenie");
        eventList.add("Spotkanie organizacyjne");
        eventList.add("Warsztaty Android");

        EditText editText = findViewById(R.id.editTextEvent);
        Button button = findViewById(R.id.buttonAdd);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewEvents);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventAdapter(eventList);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.divider);
        divider.setDrawable(drawable);
        recyclerView.addItemDecoration(divider);

        button.setOnClickListener(v -> {
            String text = editText.getText().toString();
            eventList.add(0, text);
            adapter.notifyDataSetChanged();
            editText.setText("");
        });
    }
}