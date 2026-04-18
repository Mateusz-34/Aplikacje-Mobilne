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
                "_id INTEGER PRIMARY KEY, " +
                "name TEXT NOT NULL, " +
                "quantity INTEGER NOT NULL, " +
                "category TEXT DEFAULT 'Inne')");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }

    public void add(String name, int quantity, String category) {
        ContentValues v = new ContentValues();
        v.put("name", name);
        v.put("quantity", quantity);
        v.put("category", category);
        getWritableDatabase().insert("products", null, v);
    }

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        Cursor c = getReadableDatabase().rawQuery("SELECT * FROM products", null);

        while (c.moveToNext()) {
            list.add(new Product(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getString(3)
            ));
        }
        c.close();
        return list;
    }

    public void delete(int id) {
        getWritableDatabase().delete("products", "_id=?", new String[]{String.valueOf(id)});
    }

    public void clear() {
        getWritableDatabase().delete("products", null, null);
    }
}