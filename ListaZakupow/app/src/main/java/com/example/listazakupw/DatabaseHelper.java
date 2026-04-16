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
        super(c, "products.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE products(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, quantity INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase db, int a, int b) {}

    public void add(String name, int qty) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put("name", name);
        v.put("quantity", qty);
        db.insert("products", null, v);
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM products", null);

        while (c.moveToNext()) {
            list.add(new Product(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2)
            ));
        }

        c.close();
        return list;
    }

    public void clear() {
        getWritableDatabase().delete("products", null, null);
    }
}