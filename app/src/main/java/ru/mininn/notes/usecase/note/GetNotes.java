package ru.mininn.notes.usecase.note;

import android.support.annotation.NonNull;

import java.util.List;

import ru.mininn.notes.database.NoteDatabase;
import ru.mininn.notes.entity.Note;
import ru.mininn.notes.usecase.UseCase;

public class GetNotes extends UseCase<GetNotes.RequestValues, GetNotes.ResponseValue> {

    private NoteDatabase database;

    public GetNotes(@NonNull NoteDatabase database) {
        this.database = database;
    }

    @Override
    protected void executeUseCase(final RequestValues values) {
        List<Note> note = database.getDao().getNotes();
        if (note != null) {
            ResponseValue responseValue = new ResponseValue(note);
            getUseCaseCallback().onSuccess(responseValue);
        } else {
            // Hardcoded string because keep context here not good idea.
            getUseCaseCallback().onError("Tasks not found");
        }
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

        private List<Note> notes;

        public ResponseValue(@NonNull List<Note> notes) {
            this.notes = notes;
        }

        public List<Note> getNotes() {
            return notes;
        }
    }

}
