package com.example.listazakupw;

import android.view.*;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.*;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.VH> {

    List<Product> list;

    public ProductAdapter(List<Product> list) {
        this.list = list;
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
        h.name.setText(p.getName());
        h.qty.setText("Ilość: " + p.getQuantity());
    }

    public int getItemCount() { return list.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView name, qty;

        VH(View v) {
            super(v);
            name = v.findViewById(R.id.nameTxt);
            qty = v.findViewById(R.id.qtyTxt);
        }
    }
}