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

public class SavePhotos extends UseCase<SavePhotos.RequestValues, SavePhotos.ResponseValue> {

    private Context context;

    public SavePhotos(@NonNull Context context) {
        this.context = context;
    }

    @Override
    protected void executeUseCase(SavePhotos.RequestValues requestValues) {
        ResponseValue responseValue = new ResponseValue();
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(Constants.FILE_PATH_NAME, Context.MODE_PRIVATE);
        FileOutputStream fos = null;
        for (Bitmap photo : requestValues.getPhotos()) {
            String name = UUID.randomUUID().toString().replaceAll("-", "");
            try {
                File path = new File(directory, name);
                fos = new FileOutputStream(path);
                photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
            } catch (Exception e) {
                getUseCaseCallback().onError(e.getMessage());
            } finally {
                try {
                    responseValue.addPhotoName(name);
                    fos.close();
                } catch (IOException e) {
                    getUseCaseCallback().onError(e.getMessage());
                }
            }
        }
        getUseCaseCallback().onSuccess(responseValue);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private List<Bitmap> photos;

        public RequestValues(@NonNull List<Bitmap> photos) {
            this.photos = photos;
        }

        public List<Bitmap> getPhotos() {
            return photos;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<String> photoUris;

        public ResponseValue() {
            this.photoUris = new ArrayList<>();
        }

        public void addPhotoName(String photoName) {
            this.photoUris.add(photoName);
        }
    }
}
