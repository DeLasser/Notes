package ru.mininn.notes.database;


import java.util.ArrayList;
import java.util.List;

import ru.mininn.notes.entity.Note;

public class DatabaseHelper {

    private Dao dao;

    public DatabaseHelper(NoteDatabase database) {
        dao = database.getDao();
    }

    public List<Note> getNotes() {
        List<Note> notes = dao.getNotes();
        if (notes != null) {
            for (Note note : notes) {
                note.setImageUris(dao.getImages(note.getId()));
            }
        }
        return notes;
    }

    public void saveNote(Note note) {
        dao.insertNote(note);
        dao.insertImages(note.getImageUris());
    }

}
