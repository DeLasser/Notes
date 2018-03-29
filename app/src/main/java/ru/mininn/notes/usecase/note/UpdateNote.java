package ru.mininn.notes.usecase.note;

import android.support.annotation.NonNull;

import ru.mininn.notes.database.NoteDatabase;
import ru.mininn.notes.entity.Note;
import ru.mininn.notes.usecase.UseCase;

public class UpdateNote extends UseCase<UpdateNote.RequestValues, UpdateNote.ResponseValue> {

    private NoteDatabase database;

    public UpdateNote(@NonNull NoteDatabase database) {
        this.database = database;
    }

    @Override
    protected void executeUseCase(UpdateNote.RequestValues requestValues) {
        long id = database.getDao().updateNote(requestValues.getNote().getNote());
        database.getDao().insertPhotosForNote(id, requestValues.getNote().getImages());
        getUseCaseCallback().onSuccess(new UpdateNote.ResponseValue());
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
