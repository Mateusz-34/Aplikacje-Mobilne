package com.example.organizatorwydarzen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
Imię: Jan
Nazwisko: Kowalski
PESEL: 12345678901
*/

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    ArrayList<String> lista;
    private Context context;

    public EventAdapter(Context context, ArrayList<String> lista) {
        this.context = context;
        this.lista = lista;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textViewEvent);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                String wydarzenie = lista.get(position);
                Toast.makeText(context, "Kliknięto: " + wydarzenie, Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.text.setText(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}