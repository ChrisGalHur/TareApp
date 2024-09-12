package com.chrisgalhur.tareapp.view;

import android.content.Context;

import com.chrisgalhur.tareapp.entity.Reminder;

public interface FormReminderView {

    Context getContext();

    void openDatePicker();

    void openTimePicker();

    void sendReminderSave();

    void loadReminder(Reminder reminder);

    void showError(String message);

    void showSuccess(String s);
}
