package com.chrisgalhur.tareapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.chrisgalhur.tareapp.database.DatabaseConstants;

public abstract class Task {

    //region PROPERTIES
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DatabaseConstants.COLUMN_TASK_ID)
    private int id;
    @ColumnInfo(name = DatabaseConstants.COLUMN_TASK_NAME)
    private String name;
    @ColumnInfo(name = DatabaseConstants.COLUMN_TASK_DESCRIPTION)
    private String description;
    @ColumnInfo(name = DatabaseConstants.COLUMN_TASK_COMPLETED)
    private boolean completed;
    //endregion PROPERTIES

    //region CONSTRUCTOR
    @Ignore
    protected Task(String name, String description, boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed;
    }

    protected Task(int id, String name, String description, boolean completed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
    }
    //endregion CONSTRUCTOR

    //region GETTERS
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
    //endregion GETTERS

    //region SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    //endregion SETTERS

    //region CHANGE_COMPLETE
    public void changeComplete(){
        this.completed = !this.completed;
    }
    //endregion CHANGE_COMPLETE

    //region GET_TASK_TYPE
    public abstract String getTaskType();
    //endregion GET_TASK_TYPE
}