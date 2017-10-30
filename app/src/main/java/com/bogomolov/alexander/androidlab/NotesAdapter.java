package com.bogomolov.alexander.androidlab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 26.10.2017.
 */

public class NotesAdapter extends RecyclerView.Adapter<NoteHolder> {

    Context context;

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.note_holder, parent, false);
        return new NoteHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(NoteHolder holder, int position) {
        Note note = Database.getNotes().get(position);
        holder.title.setText(note.title);

        switch (note.priority) {
            case 1:
                holder.star2.setVisibility(View.INVISIBLE);
                holder.star3.setVisibility(View.INVISIBLE);
            case 2:
                holder.star3.setVisibility(View.INVISIBLE);
        }

        holder.content.setText(note.content);
        holder.layout.setTag(new int[]{position, note.id});

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        holder.date.setText(currentDateTimeString);


        if (note.imagePath != null) {
            Uri uri = Uri.parse(note.imagePath);
            try {

                final InputStream imageStream = context.getContentResolver().openInputStream(uri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                holder.imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {

            }
        }
    }

    public NotesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return Database.getNotes().size();
    }


}