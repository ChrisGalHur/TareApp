package com.chrisgalhur.tareapp.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.chrisgalhur.tareapp.database.DatabaseConstants;

import java.time.LocalDateTime;

@Entity(tableName = DatabaseConstants.TABLE_REMINDER)
public class Reminder extends Task {

    //region PROPERTIES
    @ColumnInfo(name = DatabaseConstants.COLUMN_REMINDER_DATE)
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
