package ru.mininn.notes.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.List;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String createDate;
    private String changeDate;
    @Ignore
    private List<DatabaseImage> databaseImages;

    public Note(String name, String description, String createDate, String changeDate, List<DatabaseImage> databaseImages) {
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.changeDate = changeDate;
        this.databaseImages = databaseImages;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public List<DatabaseImage> getDatabaseImages() {
        return databaseImages;
    }

    public void setDatabaseImages(List<DatabaseImage> databaseImages) {
        this.databaseImages = databaseImages;
    }
}
