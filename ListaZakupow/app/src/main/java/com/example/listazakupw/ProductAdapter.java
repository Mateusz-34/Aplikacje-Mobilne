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
        return new VH(LayoutInflater.from(p.getContext()).inflate(R.layout.item_product, p, false));
    }

    public void onBindViewHolder(VH holder, int position) {
        Product product = list.get(position);

        holder.name.setText(product.getName());
        holder.qty.setText("Ilość: " + product.getQuantity());
        holder.category.setText("Kategoria: " + product.getCategory());

        holder.delete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                db.delete(list.get(pos).getId());
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