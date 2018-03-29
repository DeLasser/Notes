package ru.mininn.notes.usecase.note;

import android.support.annotation.NonNull;

import ru.mininn.notes.database.NoteDatabase;
import ru.mininn.notes.entity.Note;
import ru.mininn.notes.usecase.UseCase;

public class SaveNote extends UseCase<SaveNote.RequestValues, SaveNote.ResponseValue> {

    private NoteDatabase database;

    public SaveNote(@NonNull NoteDatabase database) {
        this.database = database;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        long id = database.getDao().insertNote(requestValues.getNote().getNote());
        database.getDao().insertPhotosForNote(id, requestValues.getNote().getImages());
        getUseCaseCallback().onSuccess(new ResponseValue());
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private Note note;

        public RequestValues(@NonNull Note note) {
            this.note = note;
        }

        public Note getNote() {
            return note;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {

    }
}
