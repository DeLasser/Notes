package ru.mininn.notes.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ru.mininn.notes.entity.ImageData;
import ru.mininn.notes.entity.Note;
import ru.mininn.notes.entity.NoteData;

@android.arch.persistence.room.Dao
public abstract class Dao {

    @Query("SELECT * FROM NoteData")
    public abstract List<Note> getNotes();

    @Insert
    public abstract long insertNote(NoteData note);

    @Update
    public abstract int updateNote(NoteData note);

    @Delete
    public abstract void deleteNote(NoteData note);

    @Delete
    public abstract void deleteAllImages(List<ImageData> images);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract void insertImages(List<ImageData> notes);

    public void insertPhotosForNote(long noteId, List<ImageData> images){

        for(ImageData image : images){
            image.setNoteId(noteId);
        }

        insertImages(images);
    }

}
