package com.chrisgalhur.tareapp.model;

import java.time.LocalDateTime;

public class Reminder extends Task{

    //region PROPERTIES
    private LocalDateTime reminderDate;
    //endregion PROPERTIES

    //region CONSTRUCTOR
    public Reminder(String name, String description, boolean completed, LocalDateTime reminderDate) {
        super(name, description, completed);
        this.reminderDate = reminderDate;
    }
    //endregion CONSTRUCTOR

    //region GETTERS
    public LocalDateTime getReminderDate() {
        return reminderDate;
    }
    //endregion GETTERS

    //region SETTERS
    public void setReminderDate(LocalDateTime reminderDate) {
        this.reminderDate = reminderDate;
    }
    //endregion SETTERS

    //region GET_TASK_TYPE
    @Override
    public String getTaskType() {
        return "Reminder";
    }
    //endregion GET_TASK_TYPE
}
