package com.chrisgalhur.tareapp.presenter;

public interface FormReminderPresenter {

    void onBtSelectDateClicked();

    void onBtSelectTimeClicked();

    void onBtnAcceptClicked();

    void saveReminder(String reminderName, String string, boolean b, int year, int month, int day, int hour, int minute);

    void updateReminder(int reminderId, String reminderName, String description, boolean completed, int year, int month, int day, int hour, int minute);

    void loadReminder(int reminderId);

    void clearDisposables();

    void onBtDeleteReminderClicked(int reminderId);
}
