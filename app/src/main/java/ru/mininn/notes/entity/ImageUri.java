package ru.mininn.notes.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
@Entity
public class ImageUri {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ForeignKey(entity = Note.class, parentColumns = "id", childColumns = "cityId")
    private int cityId;
    private String uri;

    public ImageUri(int cityId, String uri) {
        this.cityId = cityId;
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
