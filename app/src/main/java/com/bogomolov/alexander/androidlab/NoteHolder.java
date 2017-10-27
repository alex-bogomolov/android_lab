package com.bogomolov.alexander.androidlab;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by admin on 27.10.2017.
 */

public class NoteHolder extends RecyclerView.ViewHolder {
    public TextView title, priority, content;

    public NoteHolder(LinearLayout v) {
        super(v);
        title = (TextView) v.findViewById(R.id.noteTitle);
        priority = (TextView) v.findViewById(R.id.notePriority);
        content = (TextView) v.findViewById(R.id.noteText);
    }
}
