package com.example.listazakupw;

import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    List<Product> list;
    DatabaseHelper db;

    public ProductAdapter(List<Product> list, DatabaseHelper db) {
        this.list = list;
        this.db = db;
    }

    public void setList(List<Product> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public VH onCreateViewHolder(ViewGroup p, int v) {
        View view = LayoutInflater.from(p.getContext())
                .inflate(R.layout.item_product, p, false);
        return new VH(view);
    }

    public void onBindViewHolder(VH h, int i) {
        Product p = list.get(i);

        h.name.setText(p.name);
        h.qty.setText("Ilość: " + p.quantity);
        h.category.setText(p.category);

        h.delete.setOnClickListener(v -> {
            int pos = h.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                db.delete(list.get(pos).id);
                list.remove(pos);
                notifyItemRemoved(pos);
            }
        });
    }

    public int getItemCount() { return list.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, qty, category;
        Button delete;

        VH(View v) {
            super(v);
            name = v.findViewById(R.id.tvProductName);
            qty = v.findViewById(R.id.tvQuantity);
            category = v.findViewById(R.id.tvCategory);
            delete = v.findViewById(R.id.btnDelete);
        }
    }
}