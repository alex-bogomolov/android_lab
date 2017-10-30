package com.bogomolov.alexander.androidlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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

        adapter = new NotesAdapter(this);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_note_item:
                Intent intent = new Intent(this, AddNoteActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int[] tag = (int[]) item.getActionView().getTag();
        int position = tag[0];
        int id = tag[1];
        Database.removeNote(id);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, Database.count());
        return true;
    }
}
