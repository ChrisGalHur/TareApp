package com.chrisgalhur.tareapp.presenter.interf;

import com.chrisgalhur.tareapp.entity.Reminder;

public interface FormReminderPresenter {

    void onBtSelectDateClicked();

    void onBtSelectTimeClicked();

    void onBtnAcceptClicked();

    void saveReminder(String reminderName, String string, boolean b, int year, int month, int day, int hour, int minute);

    void updateReminder(int reminderId, String reminderName, String description, boolean completed, int year, int month, int day, int hour, int minute);

    //region SET_REMINDER_ALARM
    void setReminderAlarm(Reminder reminder);

    void loadReminder(int reminderId);

    void clearDisposables();

    void onBtDeleteReminderClicked(int reminderId);
}
