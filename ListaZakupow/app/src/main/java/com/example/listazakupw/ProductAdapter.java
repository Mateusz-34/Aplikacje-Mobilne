package com.example.listazakupw;

import android.view.*;
import android.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    List<Product> list;
    OnDeleteClickListener listener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Product product);
    }

    public ProductAdapter(List<Product> list, OnDeleteClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    public void setList(List<Product> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new VH(v);
    }

    public void onBindViewHolder(VH holder, int position) {
        Product product = list.get(position);

        holder.name.setText(product.getName());
        holder.qty.setText("Ilość: " + product.getQuantity());
        holder.category.setText("Kategoria: " + product.getCategory());

        holder.delete.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {
                listener.onDeleteClick(list.get(pos));
            }
        });
    }

    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, qty, category;
        ImageButton delete;

        VH(View v) {
            super(v);
            name = v.findViewById(R.id.tvProductName);
            qty = v.findViewById(R.id.tvQuantity);
            category = v.findViewById(R.id.tvCategory);
            delete = v.findViewById(R.id.btnDelete);
        }
    }
}