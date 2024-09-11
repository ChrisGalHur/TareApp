package com.chrisgalhur.tareapp.model;

import com.chrisgalhur.tareapp.entity.Reminder;

public interface FormReminderModel {
    void saveReminder(Reminder reminderToSave);

    Reminder getReminder(int reminderId);
}
