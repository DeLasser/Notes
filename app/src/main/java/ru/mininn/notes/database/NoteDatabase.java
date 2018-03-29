package ru.mininn.notes.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ru.mininn.notes.entity.ImageData;
import ru.mininn.notes.entity.NoteData;

@Database(entities = { NoteData.class, ImageData.class }, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract Dao getDao();
}
