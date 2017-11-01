package com.bogomolov.alexander.androidlab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by admin on 27.10.2017.
 */

public class Note {
    public String title;
    public String content;
    public int priority;
    public Date createdAt;
    public byte[] image;
    public int id;

    public Note(int id, String title, String content, int priority, byte[] image, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.image = image;
        this.createdAt = date;
    }

    public void setImage(String imagePath, Context context) {
        Uri uri = Uri.parse(imagePath);
        try {
            final InputStream imageStream = context.getContentResolver().openInputStream(uri);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = imageStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();

            this.image = buffer.toByteArray();
        } catch (IOException e) {

        }
    }
}
