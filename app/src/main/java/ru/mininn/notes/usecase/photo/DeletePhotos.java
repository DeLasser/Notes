package ru.mininn.notes.usecase.photo;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ru.mininn.notes.Constants;
import ru.mininn.notes.usecase.UseCase;

public class DeletePhotos extends UseCase<DeletePhotos.RequestValues, DeletePhotos.ResponseValue> {
    private Context context;

    public DeletePhotos(@NonNull Context context) {
        this.context = context;
    }

    @Override
    protected void executeUseCase(DeletePhotos.RequestValues requestValues) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(Constants.FILE_PATH_NAME, Context.MODE_PRIVATE);
        for (String photo : requestValues.getPhotos()) {
            try {
                File path = new File(directory, photo);
                boolean result = path.delete();
                if (!result) {
                    getUseCaseCallback().onError("Photo not deleted");
                }
            } catch (Exception e) {
                getUseCaseCallback().onError(e.getMessage());
            }
        }
        getUseCaseCallback().onSuccess(new DeletePhotos.ResponseValue());
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private List<String> photoUris;

        public RequestValues(@NonNull List<String> photoUris) {
            this.photoUris = photoUris;
        }

        public List<String> getPhotos() {
            return photoUris;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

    }
}

