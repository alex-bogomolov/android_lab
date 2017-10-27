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
    public int id;

    private static int nextId = 1;

    public Note(String title, String content, int priority, String imagePath) {
        id = nextId;
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.imagePath = imagePath;
        this.createdAt = new Date();

        nextId++;
    }
}
