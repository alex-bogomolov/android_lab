package com.bogomolov.alexander.androidlab;

import android.app.DialogFragment;
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
    private NotesAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        this.recyclerView = (RecyclerView) findViewById(R.id.notes_recycler_view);
        this.recyclerView.setHasFixedSize(true);

        this.layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(this.layoutManager);

        adapter = new NotesAdapter(this, Database.getNotes());
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
                Intent intent = new Intent(this, NoteEditorActivity.class);
                intent.putExtra(NoteEditorActivity.NOTE_ID, -1);
                startActivity(intent);
                break;
            case R.id.filter_by_priority:
                DialogFragment priorityDialog = new PriorityFilterDialog();
                priorityDialog.show(getFragmentManager(), "Filter by priority");
                break;
            case R.id.filter_by_content:
                DialogFragment contentDialog = new ContentFilterDialog();
                contentDialog.show(getFragmentManager(), "Filter by content");
                break;
            case R.id.clear_filters:
                adapter.dataset = Database.getNotes();
                adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String action = item.getTitle().toString();

        if (action.equals("Delete")) {
            int[] tag = (int[]) item.getActionView().getTag();
            int position = tag[0];
            int id = tag[1];
            adapter.removeById(id);
            Database.removeNote(id);
            adapter.notifyDataSetChanged();
            return true;
        } else if (action.equals("Edit")) {
            int[] tag = (int[]) item.getActionView().getTag();
            int position = tag[0];
            int id = tag[1];

            Intent intent = new Intent(this, NoteEditorActivity.class);
            intent.putExtra(NoteEditorActivity.NOTE_ID, id);
            startActivity(intent);
        }

        return true;
    }

    public void filterByPriority(int priority) {
        adapter.dataset = Database.filterByPriority(priority);
        adapter.notifyDataSetChanged();
    }

    public void filterByContent(String content) {
        adapter.dataset = Database.filterByContent(content);
        adapter.notifyDataSetChanged();
    }
}
