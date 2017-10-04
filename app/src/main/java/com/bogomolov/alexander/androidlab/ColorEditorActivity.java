package com.bogomolov.alexander.androidlab;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class ColorEditorActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    TextView colorPanel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_editor);

        this.redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        this.greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        this.blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);

        this.redSeekBar.setMin(0);
        this.redSeekBar.setMax(255);

        this.greenSeekBar.setMin(0);
        this.greenSeekBar.setMax(255);

        this.blueSeekBar.setMin(0);
        this.blueSeekBar.setMax(255);

        this.colorPanel = (TextView) findViewById(R.id.colorPanel);

        this.redSeekBar.setOnSeekBarChangeListener(this);
        this.greenSeekBar.setOnSeekBarChangeListener(this);
        this.blueSeekBar.setOnSeekBarChangeListener(this);

        this.changeColor();
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.changeColor();
    }

    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    private void changeColor() {
        int r = this.redSeekBar.getProgress();
        int g = this.greenSeekBar.getProgress();
        int b = this.blueSeekBar.getProgress();

        int color = Color.rgb(r, g, b);
        this.colorPanel.setBackgroundColor(color);
    }
}
