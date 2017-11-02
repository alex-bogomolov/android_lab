package com.bogomolov.alexander.androidlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Database.getDatabase(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openColorEditor(View view) {
        Intent intent = new Intent(this, ColorEditorActivity.class);
        startActivity(intent);
    }

    public void openCalculator(View view) {
        Intent intent = new Intent(this, CalculatorActivity.class);
        startActivity(intent);
    }

    public void openNotes(View view) {
        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
    }
}
