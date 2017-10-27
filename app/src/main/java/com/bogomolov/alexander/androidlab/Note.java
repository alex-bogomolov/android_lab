package com.bogomolov.alexander.androidlab;

import java.util.Date;

/**
 * Created by admin on 27.10.2017.
 */

public class Note {
    public String title;
    public String content;
    public int priority;
    public Date createdAt;
    public String imagePath;

    public Note(String title, String content, int priority, String imagePath) {
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.imagePath = imagePath;
        this.createdAt = new Date();
    }
}
