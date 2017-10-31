package com.bogomolov.alexander.androidlab;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by admin on 31.10.2017.
 */

public final class NotesContract {
    private NotesContract() {}

    public static class NotesEntry implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String COLUMN_NAME_PRIORITY = "priority";
        public static final String COLUMN_NAME_CREATED_AT = "created_at";
        public static final String COLUMN_NAME_IMAGE_PATH = "image_path";
    }

    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + NotesEntry.TABLE_NAME +
            " (" + NotesEntry._ID + " INTEGER PRIMARY KEY, " + NotesEntry.COLUMN_NAME_TITLE + " TEXT, " +
            NotesEntry.COLUMN_NAME_CONTENT + " TEXT, " + NotesEntry.COLUMN_NAME_PRIORITY + " INTEGER, " +
            NotesEntry.COLUMN_NAME_CREATED_AT + " TEXT, " + NotesEntry.COLUMN_NAME_IMAGE_PATH + " TEXT)";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + NotesEntry.TABLE_NAME;


}
