package com.chrisgalhur.tareapp.model;

public abstract class Task {

    //region PROPERTIES
    private String name;
    private String description;
    private boolean completed;
    //endregion PROPERTIES

    //region CONSTRUCTOR
    public Task(String name, String description, boolean completed) {
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