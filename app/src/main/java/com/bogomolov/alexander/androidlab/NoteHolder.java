package com.bogomolov.alexander.androidlab;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by admin on 27.10.2017.
 */

public class NoteHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public TextView title, priority, content, date;
    public LinearLayout layout;
    public ImageView imageView, star1, star2, star3;

    public NoteHolder(LinearLayout v) {
        super(v);
        this.layout = v;
        title = (TextView) v.findViewById(R.id.noteTitle);
        content = (TextView) v.findViewById(R.id.noteText);
        imageView = (ImageView) v.findViewById(R.id.note_image);
        date = (TextView) v.findViewById(R.id.note_date);

        star1 = (ImageView) v.findViewById(R.id.star_1);
        star2 = (ImageView) v.findViewById(R.id.star_2);
        star3 = (ImageView) v.findViewById(R.id.star_3);

        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Choose an option");
        MenuItem deleteItem = menu.add(0, v.getId(), 0, "Delete");
        deleteItem.setActionView(this.layout);
    }


}
