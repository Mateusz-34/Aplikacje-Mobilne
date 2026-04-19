/*
Imię: Mateusz
Nazwisko: Ostróżka
PESEL: 00000000000
*/

package com.example.listazakupw;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import java.util.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context c) {
        super(c, "products.db", null, 2);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE products (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "quantity INTEGER NOT NULL, " +
                "category TEXT DEFAULT 'Inne')");
    }

    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }

    public void insertProduct(String name, int quantity, String category) {
        ContentValues v = new ContentValues();
        v.put("name", name);
        v.put("quantity", quantity);
        v.put("category", category);
        getWritableDatabase().insert("products", null, v);
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM products", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int quantity = cursor.getInt(2);
                String category = cursor.getString(3);

                productList.add(new Product(id, name, quantity, category));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return productList;
    }

    public void delete(int id) {
        getWritableDatabase().delete("products", "_id=?", new String[]{String.valueOf(id)});
    }

    public void clear() {
        getWritableDatabase().delete("products", null, null);
    }
}