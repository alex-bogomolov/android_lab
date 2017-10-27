package com.bogomolov.alexander.androidlab;

import java.util.ArrayList;

/**
 * Created by admin on 27.10.2017.
 */

public class Database {
    private static ArrayList<Note> notes;

    public static void prepare() {
        notes = new ArrayList<Note>();

        notes.add(new Note("Lorem ipsum dolor sit amet", "Sed mauris nibh, tempus eu lectus in, auctor blandit tortor", 1, null));
        notes.add(new Note("Morbi cursus finibus nibh", "Donec mauris erat, porta malesuada velit sed, semper sodales lacus.", 2, null));
        notes.add(new Note("Morbi at purus ut lorem mollis porta", "Donec nibh arcu, volutpat id accumsan vitae, commodo sit amet nulla.", 3, null));
        notes.add(new Note("Donec efficitur mauris in feugiat auctor", "Sed efficitur eros sed commodo rutrum. Fusce at dignissim magna.", 3, null));
        notes.add(new Note("Aliquam id dolor aliquet, ultrices lectus id, fermentum tortor", "Donec quam justo, tempor sit amet tristique sit amet, feugiat vitae turpis. Sed at tortor quis purus gravida ultrices ac a mauris.", 3, null));

    }

    public static ArrayList<Note> getNotes() {
        return notes;
    }

    public static void addNote(Note note) {
        notes.add(note);
    }

    public static void removeNote(int id) {
        int position = -1;

        for(int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (note.id == id) {
                position = i;
                break;
            }
        }

        if (position != -1) {
            notes.remove(position);
        }
    }

    public static int count() {
        return notes.size();
    }
}
