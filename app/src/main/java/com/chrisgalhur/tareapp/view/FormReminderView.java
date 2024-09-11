package com.chrisgalhur.tareapp.view;

import com.chrisgalhur.tareapp.entity.Reminder;

public interface FormReminderView {
    void openDatePicker();

    void openTimePicker();

    void sendReminder();

    void loadReminder(Reminder reminder);
}
