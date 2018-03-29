package ru.mininn.notes.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
@Entity
public class DatabaseImage {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int cityId;
    private String uri;
}
