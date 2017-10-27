package com.bogomolov.alexander.androidlab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by admin on 26.10.2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NoteHolder> {

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.note_holder, parent, false);
        return new NoteHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        Note note = Database.getNotes().get(position);
        holder.title.setText(note.title);
        holder.priority.setText(String.valueOf(note.priority));
        holder.content.setText(note.content);
    }

    @Override
    public int getItemCount() {
        return Database.getNotes().size();
    }


}
