package com.example.listykrajeprodukty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView countryRecyclerView;
    private RecyclerView productRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryRecyclerView = findViewById(R.id.countryRecyclerView);
        productRecyclerView = findViewById(R.id.productRecyclerView);

        List<Country> country = new ArrayList<>();
        country.add(new Country("Polska"));
        country.add(new Country("Niemcy"));
        country.add(new Country("Francja"));
        country.add(new Country("Hiszpania"));
        country.add(new Country("Włochy"));
        country.add(new Country("Japonia"));
        country.add(new Country("Kanada"));
        country.add(new Country("Australia"));
        country.add(new Country("Brazylia"));
        country.add(new Country("Egipt"));

        CountryAdapter countryadapter = new CountryAdapter(country);

        countryRecyclerView.setAdapter(countryadapter);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> product = new ArrayList<>();
        product.add(new Product("Chleb", 4.20F));
        product.add(new Product("Mleko", 3.50F));
        product.add(new Product("Masło", 5.00F));
        product.add(new Product("Ser żółty", 10.00F));
        product.add(new Product("Jajka", 3.00F));
        product.add(new Product("Szynka", 8.50F));
        product.add(new Product("Jabłka czerwone", 2.00F));
        product.add(new Product("Makaron spaghetti", 2.50F));
        product.add(new Product("Kawa mielona", 3.00F));
        product.add(new Product("Czekolada mleczna", 5.00F));

        ProductAdapter productadapter = new ProductAdapter(product);

        productRecyclerView.setAdapter(productadapter);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}