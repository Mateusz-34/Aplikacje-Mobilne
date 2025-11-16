package com.example.bazydane;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private MainActivity activity;

    public NoteAdapter(List<Note> noteList, MainActivity activity) {
        this.noteList = noteList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note currentNote = noteList.get(position);

        holder.noteID.setText("ID: " + currentNote.getId());
        holder.noteText.setText("Treść: " + currentNote.getText());

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(holder.itemView.getContext(), "Usunietono notatkę o ID: " + currentNote.getId(), Toast.LENGTH_SHORT).show();

            activity.deleteNote(currentNote.getId());
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        public TextView noteID;
        public TextView noteText;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteID = itemView.findViewById(R.id.noteID);
            noteText = itemView.findViewById(R.id.noteText);
        }
    }
}