package com.bogomolov.alexander.androidlab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openColorEditor(View view) {
        Intent intent = new Intent(this, ColorEditorActivity.class);
        startActivity(intent);
    }
}
