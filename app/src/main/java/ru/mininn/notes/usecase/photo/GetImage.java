package ru.mininn.notes.usecase.photo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import ru.mininn.notes.Constants;
import ru.mininn.notes.usecase.UseCase;

public class GetImage extends UseCase<GetImage.RequestValues, GetImage.ResponseValue> {

    public GetImage() {
    }

    @Override
    protected void executeUseCase(GetImage.RequestValues requestValues) {
        List<Bitmap> photos = new ArrayList<>();
        for (String photoName : requestValues.getPhotoNames()) {
            try {
                File file = new File(Constants.FILE_PATH_NAME, photoName);
                Bitmap photo = BitmapFactory.decodeStream(new FileInputStream(file));
                photos.add(photo);

            } catch (FileNotFoundException e) {
                getUseCaseCallback().onError(e.getMessage());
            }
        }
        getUseCaseCallback().onSuccess(new ResponseValue(photos));
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private List<String> photoUris;

        public RequestValues(@NonNull List<String> photoUris) {
            this.photoUris = photoUris;
        }

        public List<String> getPhotoNames() {
            return photoUris;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<Bitmap> photos;

        public ResponseValue(@NonNull List<Bitmap> photos) {
            this.photos = photos;
        }

        public List<Bitmap> getPhotos() {
            return photos;
        }
    }

}
