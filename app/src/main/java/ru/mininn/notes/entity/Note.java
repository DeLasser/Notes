package ru.mininn.notes.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;


public class Note {
    @Embedded
    private NoteData note;

    @Relation(parentColumn = "id", entityColumn = "noteId")
    private List<ImageData> images;

    public Note() {
    }

    public Note(NoteData note, List<ImageData> images) {
        this.note = note;
        this.images = images;
    }

    public NoteData getNote() {
        return note;
    }

    public void setNote(NoteData note) {
        this.note = note;
    }

    public List<ImageData> getImages() {
        return images;
    }

    public void setImages(List<ImageData> images) {
        this.images = images;
    }
}


