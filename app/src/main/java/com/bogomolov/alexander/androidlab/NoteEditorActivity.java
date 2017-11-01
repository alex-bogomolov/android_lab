package com.bogomolov.alexander.androidlab;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Date;

public class NoteEditorActivity extends AppCompatActivity {
    EditText titleInput, contentInput;
    RadioGroup priorityInput;
    RadioButton priority1, priority2, priority3;

    String imagePath;

    int noteId;
    Note note;

    final static String NOTE_ID="NOTE_ID";

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.note_editor_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        this.titleInput = (EditText) findViewById(R.id.note_editor_note_title);
        this.contentInput = (EditText) findViewById(R.id.note_editor_note_content);
        this.priorityInput = (RadioGroup) findViewById(R.id.note_editor_note_priority);

        this.priority1 = (RadioButton) findViewById(R.id.note_editor_priority_1);
        this.priority2 = (RadioButton) findViewById(R.id.note_editor_priority_2);
        this.priority3 = (RadioButton) findViewById(R.id.note_editor_priority_3);

        Intent intent = getIntent();

        noteId = intent.getIntExtra(NOTE_ID, -1);

        if (noteId != -1) {
            note = Database.getDatabase().getNoteById(noteId);

            titleInput.setText(note.title);
            contentInput.setText(note.content);

            switch (note.priority) {
                case 1:
                    this.priority1.setChecked(true);
                    break;
                case 2:
                    this.priority2.setChecked(true);
                    break;
                case 3:
                    this.priority3.setChecked(true);
                    break;
            }
        }
    }

    public void saveNote(View view) {
        if (this.note == null) {
            String title = titleInput.getText().toString();
            String content = contentInput.getText().toString();
            RadioButton checkedPriorityRadioButton = (RadioButton) findViewById(priorityInput.getCheckedRadioButtonId());
            int priority = Integer.parseInt((String) checkedPriorityRadioButton.getTag());
            Note newNote = new Note(0, title, content, priority, null, new Date());

            if (this.imagePath != null) {
                newNote.setImage(this.imagePath, getApplicationContext());
            }

            Database.getDatabase().addNote(newNote);
            NavUtils.navigateUpFromSameTask(this);
        } else {
            note.title = titleInput.getText().toString();
            note.content = contentInput.getText().toString();
            RadioButton checkedPriorityRadioButton = (RadioButton) findViewById(priorityInput.getCheckedRadioButtonId());
            note.priority = Integer.parseInt((String) checkedPriorityRadioButton.getTag());

            if (this.imagePath != null) {
                note.setImage(this.imagePath, getApplicationContext());
            }

            Database.getDatabase().updateNote(note);
            NavUtils.navigateUpFromSameTask(this);
        }
    }

    public void addImage(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            this.imagePath = data.getData().toString();
        }
    }
}
