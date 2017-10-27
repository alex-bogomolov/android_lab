package com.bogomolov.alexander.androidlab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class NotesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        this.recyclerView = (RecyclerView) findViewById(R.id.notesRecyclerView);
        this.recyclerView.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(this.layoutManager);

        adapter = new NotesAdapter();
        recyclerView.setAdapter(adapter);
    }
}
