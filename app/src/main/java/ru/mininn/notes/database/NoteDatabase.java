package ru.mininn.notes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.mininn.notes.entity.ImageUri;
import ru.mininn.notes.entity.Note;

@Database(entities = { Note.class, ImageUri.class }, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract Dao getDao();
}
