package com.bogomolov.alexander.androidlab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 27.10.2017.
 */

public class Database {
    private static Database singleton;

    private NotesDbHelper notesDbHelper;

    public static Database getDatabase(Context context) {
        if (singleton == null) {
            singleton = new Database(context);
        }
        return singleton;
    }

    public static Database getDatabase() {
        return singleton;
    }

    private Database(Context context) {
        this.notesDbHelper = new NotesDbHelper(context);
    }

    public void seed() {
        deleteAllNotes();
        ArrayList<Note> notes = new ArrayList<Note>();

        Date currentDate = new Date();

        notes.add(new Note(0, "Lorem ipsum dolor sit amet", "Sed mauris nibh, tempus eu lectus in, auctor blandit tortor", 1, null, currentDate));
        notes.add(new Note(0, "Morbi cursus finibus nibh", "Donec mauris erat, porta malesuada velit sed, semper sodales lacus.", 2, null, currentDate));
        notes.add(new Note(0, "Morbi at purus ut lorem mollis porta", "Donec nibh arcu, volutpat id accumsan vitae, commodo sit amet nulla.", 3, null, currentDate));
        notes.add(new Note(0, "Donec efficitur mauris in feugiat auctor", "Sed efficitur eros sed commodo rutrum. Fusce at dignissim magna.", 3, null, currentDate));
        notes.add(new Note(0, "Aliquam id dolor aliquet, ultrices lectus id, fermentum tortor", "Donec quam justo, tempor sit amet tristique sit amet, feugiat vitae turpis. Sed at tortor quis purus gravida ultrices ac a mauris.", 3, null, currentDate));

        for (int i = 0; i < notes.size(); i++) {
            saveNoteToDb(notes.get(i));
        }
    }

    private void saveNoteToDb(Note note) {
        SQLiteDatabase db = this.notesDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesContract.NotesEntry.COLUMN_NAME_TITLE, note.title);
        values.put(NotesContract.NotesEntry.COLUMN_NAME_CONTENT, note.content);
        values.put(NotesContract.NotesEntry.COLUMN_NAME_PRIORITY, note.priority);

        String dateString = DateFormat.getDateTimeInstance().format(note.createdAt);

        values.put(NotesContract.NotesEntry.COLUMN_NAME_CREATED_AT, dateString);
        values.put(NotesContract.NotesEntry.COLUMN_NAME_IMAGE_PATH, note.imagePath);

        db.insert(NotesContract.NotesEntry.TABLE_NAME, null, values);
    }

    public Note getNoteById(int id) {
        SQLiteDatabase db = this.notesDbHelper.getReadableDatabase();

        String[] projection = {
                NotesContract.NotesEntry._ID, NotesContract.NotesEntry.COLUMN_NAME_TITLE,
                NotesContract.NotesEntry.COLUMN_NAME_CONTENT, NotesContract.NotesEntry.COLUMN_NAME_PRIORITY,
                NotesContract.NotesEntry.COLUMN_NAME_CREATED_AT, NotesContract.NotesEntry.COLUMN_NAME_IMAGE_PATH
        };

        String selection = NotesContract.NotesEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.query(NotesContract.NotesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Note note = null;

        if(cursor.moveToNext()) {
            note = getNoteFromCursor(cursor);
        }

        cursor.close();

        return note;
    }

    public void addNote(Note note) {
        saveNoteToDb(note);
    }

    public ArrayList<Note> getNotes() {
        SQLiteDatabase db = this.notesDbHelper.getReadableDatabase();

        String[] projection = {
            NotesContract.NotesEntry._ID, NotesContract.NotesEntry.COLUMN_NAME_TITLE,
                NotesContract.NotesEntry.COLUMN_NAME_CONTENT, NotesContract.NotesEntry.COLUMN_NAME_PRIORITY,
                NotesContract.NotesEntry.COLUMN_NAME_CREATED_AT, NotesContract.NotesEntry.COLUMN_NAME_IMAGE_PATH
        };

        Cursor cursor = db.query(NotesContract.NotesEntry.TABLE_NAME, projection, null, null, null, null, null);

        ArrayList<Note> notes = new ArrayList<>();

        while(cursor.moveToNext()) {
            notes.add(getNoteFromCursor(cursor));
        }

        cursor.close();

        return notes;
    }

    public void removeNote(int id) {
        SQLiteDatabase db = this.notesDbHelper.getWritableDatabase();

        String selection = NotesContract.NotesEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        db.delete(NotesContract.NotesEntry.TABLE_NAME, selection, selectionArgs);
    }

    public ArrayList<Note> filterByPriority(int priority) {
        SQLiteDatabase db = this.notesDbHelper.getReadableDatabase();

        String[] projection = {
                NotesContract.NotesEntry._ID, NotesContract.NotesEntry.COLUMN_NAME_TITLE,
                NotesContract.NotesEntry.COLUMN_NAME_CONTENT, NotesContract.NotesEntry.COLUMN_NAME_PRIORITY,
                NotesContract.NotesEntry.COLUMN_NAME_CREATED_AT, NotesContract.NotesEntry.COLUMN_NAME_IMAGE_PATH
        };

        String selection = NotesContract.NotesEntry.COLUMN_NAME_PRIORITY + " = ?";
        String[] selectionArgs = { String.valueOf(priority) };

        Cursor cursor = db.query(NotesContract.NotesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        ArrayList<Note> notes = new ArrayList<>();

        while(cursor.moveToNext()) {
            notes.add(getNoteFromCursor(cursor));
        }

        cursor.close();

        return notes;
    }

    public ArrayList<Note> filterByContent(String content) {
        SQLiteDatabase db = this.notesDbHelper.getReadableDatabase();

        String[] projection = {
                NotesContract.NotesEntry._ID, NotesContract.NotesEntry.COLUMN_NAME_TITLE,
                NotesContract.NotesEntry.COLUMN_NAME_CONTENT, NotesContract.NotesEntry.COLUMN_NAME_PRIORITY,
                NotesContract.NotesEntry.COLUMN_NAME_CREATED_AT, NotesContract.NotesEntry.COLUMN_NAME_IMAGE_PATH
        };

        String selection = "LOWER(" + NotesContract.NotesEntry.COLUMN_NAME_TITLE + ") LIKE ? OR LOWER(" + NotesContract.NotesEntry.COLUMN_NAME_CONTENT + ") LIKE ?";

        String predicate = "%" + content.toLowerCase() + "%";

        String[] selectionArgs = { predicate, predicate };

        Cursor cursor = db.query(NotesContract.NotesEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        ArrayList<Note> notes = new ArrayList<>();

        while(cursor.moveToNext()) {
            notes.add(getNoteFromCursor(cursor));
        }

        cursor.close();

        return notes;
    }

    public void updateNote(Note note) {
        SQLiteDatabase db = this.notesDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NotesContract.NotesEntry.COLUMN_NAME_TITLE, note.title);
        values.put(NotesContract.NotesEntry.COLUMN_NAME_CONTENT, note.content);
        values.put(NotesContract.NotesEntry.COLUMN_NAME_PRIORITY, note.priority);
        values.put(NotesContract.NotesEntry.COLUMN_NAME_CREATED_AT, DateFormat.getDateTimeInstance().format(note.createdAt));
        values.put(NotesContract.NotesEntry.COLUMN_NAME_IMAGE_PATH, note.imagePath);

        String selection = NotesContract.NotesEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(note.id) };

        db.update(NotesContract.NotesEntry.TABLE_NAME, values, selection, selectionArgs);

    }

    private Note getNoteFromCursor(Cursor cursor) {
        try {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(NotesContract.NotesEntry._ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(NotesContract.NotesEntry.COLUMN_NAME_TITLE));
            String c = cursor.getString(cursor.getColumnIndexOrThrow(NotesContract.NotesEntry.COLUMN_NAME_CONTENT));
            int p = cursor.getInt(cursor.getColumnIndexOrThrow(NotesContract.NotesEntry.COLUMN_NAME_PRIORITY));
            Date createdAt = DateFormat.getDateTimeInstance().parse(cursor.getString(cursor.getColumnIndexOrThrow(NotesContract.NotesEntry.COLUMN_NAME_CREATED_AT)));
            String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(NotesContract.NotesEntry.COLUMN_NAME_IMAGE_PATH));

            return new Note(id, title, c, p, imagePath, createdAt);
        } catch (ParseException e) {
            return null;
        }
    }

    public void deleteAllNotes() {
        SQLiteDatabase db = this.notesDbHelper.getWritableDatabase();

        db.delete(NotesContract.NotesEntry.TABLE_NAME, null, null);
    }
}
