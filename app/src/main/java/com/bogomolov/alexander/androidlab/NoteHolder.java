package com.bogomolov.alexander.androidlab;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by admin on 27.10.2017.
 */

public class NoteHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public TextView title, priority, content;
    public LinearLayout layout;

    public NoteHolder(LinearLayout v) {
        super(v);
        this.layout = v;
        title = (TextView) v.findViewById(R.id.noteTitle);
        priority = (TextView) v.findViewById(R.id.notePriority);
        content = (TextView) v.findViewById(R.id.noteText);

        v.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Choose an option");
        MenuItem deleteItem = menu.add(0, v.getId(), 0, "Delete");
        deleteItem.setActionView(this.layout);
    }


}
