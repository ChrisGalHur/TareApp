package com.chrisgalhur.tareapp.model;

import android.content.Context;

import com.chrisgalhur.tareapp.database.DatabaseHelper;
import com.chrisgalhur.tareapp.entity.Reminder;

public class FormReminderModelImpl implements FormReminderModel {

    private final DatabaseHelper databaseHelper;

    //region CONSTRUCTOR
    public FormReminderModelImpl(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }
    //endregion CONSTRUCTOR

    //region SAVE_REMINDER
    @Override
    public void saveReminder(Reminder reminderToSave) {
        databaseHelper.saveReminder(reminderToSave);
    }
    //endregion SAVE_REMINDER
}
