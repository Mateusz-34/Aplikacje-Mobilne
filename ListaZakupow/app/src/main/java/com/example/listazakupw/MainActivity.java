package com.example.listazakupw;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.*;
import androidx.recyclerview.widget.*;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    ProductAdapter adapter;
    List<Product> list;

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.etProductName);
        EditText qty = findViewById(R.id.etProductQuantity);
        Spinner spinner = findViewById(R.id.spinnerCategory);
        Button add = findViewById(R.id.btnAdd);
        Button clear = findViewById(R.id.btnClear);
        RecyclerView rv = findViewById(R.id.rvProducts);

        db = new DatabaseHelper(this);
        list = db.getAllProducts();

        adapter = new ProductAdapter(list, db);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        add.setOnClickListener(v -> {

            String n = name.getText().toString();
            String q = qty.getText().toString();
            String c = spinner.getSelectedItem().toString();

            if (n.isEmpty()) {
                Toast.makeText(this, R.string.err_name, Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(q);
            } catch (Exception e) {
                Toast.makeText(this, R.string.err_qty, Toast.LENGTH_SHORT).show();
                return;
            }

            if (quantity <= 0) {
                Toast.makeText(this, R.string.err_qty, Toast.LENGTH_SHORT).show();
                return;
            }

            Product product = new Product(0, n, quantity, c);

            db.insertProduct(product.getName(), product.getQuantity(), product.getCategory());

            refresh();

            name.setText("");
            qty.setText("");
        });

        clear.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.delete_confirm)
                    .setPositiveButton(R.string.yes, (d,i)->{
                        db.clear();
                        refresh();
                    })
                    .setNegativeButton(R.string.no, null)
                    .show();
        });
    }

    void refresh() {
        list = db.getAllProducts();
        adapter.setList(list);
    }
}