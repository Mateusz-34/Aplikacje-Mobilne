package com.example.wyborimienia;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MultiAutoCompleteTextView multiAutoComplete;
    private Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multiAutoComplete = findViewById(R.id.multiAutoComplete);
        btnConfirm = findViewById(R.id.btnConfirm);

        String[] names = getResources().getStringArray(R.array.name_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                names
        );

        multiAutoComplete.setAdapter(adapter);

        multiAutoComplete.setTokenizer(
                new MultiAutoCompleteTextView.CommaTokenizer()
        );

        multiAutoComplete.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        String selected = parent.getItemAtPosition(position).toString();

                        Toast.makeText(MainActivity.this,
                                "Wybrano z listy: " + selected,
                                Toast.LENGTH_SHORT).show();
                    }
                }
        );

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedNames = multiAutoComplete.getText().toString();

                Toast.makeText(MainActivity.this,
                        "Wybrano: " + selectedNames,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}