package com.example.listazakupw;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.*;
import androidx.recyclerview.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ProductAdapter adapter;
    List<Product> productList;

    EditText productNameInput, quantityInput;
    Button addProductButton, clearListButton;
    RecyclerView productsRecyclerView;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        productNameInput = findViewById(R.id.productNameInput);
        quantityInput = findViewById(R.id.quantityInput);
        addProductButton = findViewById(R.id.addProductButton);
        clearListButton = findViewById(R.id.clearListButton);
        productsRecyclerView = findViewById(R.id.productsRecyclerView);

        databaseHelper = new DatabaseHelper(this);

        productList = databaseHelper.getAllProducts();
        adapter = new ProductAdapter(productList);

        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productsRecyclerView.setAdapter(adapter);

        addProductButton.setOnClickListener(v -> {
            String name = productNameInput.getText().toString();
            String qty = quantityInput.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(this, R.string.err_name, Toast.LENGTH_SHORT).show();
                return;
            }

            if (qty.isEmpty() || Integer.parseInt(qty) <= 0) {
                Toast.makeText(this, R.string.err_qty, Toast.LENGTH_SHORT).show();
                return;
            }

            databaseHelper.add(name, Integer.parseInt(qty));
            refresh();

            productNameInput.setText("");
            quantityInput.setText("");
        });

        clearListButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.dialog)
                    .setPositiveButton(R.string.yes, (d,i)->{
                        databaseHelper.clear();
                        refresh();
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        });
    }

    void refresh() {
        productList = databaseHelper.getAllProducts();
        adapter.setList(productList);
    }
}