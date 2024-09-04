package com.chrisgalhur.tareapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public abstract class Task {

    //region PROPERTIES
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "completed")
    private boolean completed;
    //endregion PROPERTIES

    //region CONSTRUCTOR
    protected Task(String name, String description, boolean completed) {
        this.name = name;
        this.description = description;
        this.completed = completed;
    }
    //endregion CONSTRUCTOR

    //region GETTERS
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