package ru.mininn.notes.usecase.note;

import android.support.annotation.NonNull;

import ru.mininn.notes.database.NoteDatabase;
import ru.mininn.notes.entity.Note;
import ru.mininn.notes.usecase.UseCase;

public class DeleteNote extends UseCase<DeleteNote.RequestValues, DeleteNote.ResponseValue> {
    private NoteDatabase database;

    public DeleteNote(@NonNull NoteDatabase database) {
        this.database = database;
    }

    @Override
    protected void executeUseCase(DeleteNote.RequestValues requestValues) {
        database.getDao().deleteAllImages(requestValues.getNote().getImages());
        database.getDao().deleteNote(requestValues.getNote().getNote());
        getUseCaseCallback().onSuccess(new DeleteNote.ResponseValue());
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
