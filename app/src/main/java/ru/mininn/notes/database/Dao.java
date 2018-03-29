package ru.mininn.notes.database;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.media.Image;

import java.util.List;

import ru.mininn.notes.entity.ImageUri;
import ru.mininn.notes.entity.Note;

public interface Dao {

    @Query("SELECT * FROM Note")
    List<Note> getNotes();

    @Query("SELECT * FROM ImageUri WHERE cityId = :cityId")
    List<ImageUri> getImages(int cityId);

    @Insert
    void insertNote(Note note);

    @Insert
    void insertImages(List<ImageUri> images);

}
