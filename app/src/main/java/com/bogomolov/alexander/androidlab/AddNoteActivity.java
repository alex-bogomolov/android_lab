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

public class AddNoteActivity extends AppCompatActivity {
    EditText titleInput, contentInput;
    RadioGroup priorityInput;

    String imagePath;

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.add_note_toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        this.titleInput = (EditText) findViewById(R.id.add_note_title);
        this.contentInput = (EditText) findViewById(R.id.add_note_content);
        this.priorityInput = (RadioGroup) findViewById(R.id.add_note_priority);
    }

    public void saveNote(View view) {
        String title = titleInput.getText().toString();
        String content = contentInput.getText().toString();
        RadioButton checkedPriorityRadioButton = (RadioButton) findViewById(priorityInput.getCheckedRadioButtonId());
        int priority = Integer.parseInt((String) checkedPriorityRadioButton.getTag());
        Note newNote = new Note(title, content, priority, imagePath);
        Database.addNote(newNote);
        NavUtils.navigateUpFromSameTask(this);
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
