package com.chrisgalhur.tareapp.presenter;

public interface FormReminderPresenter {
    void onBtSelectDateClicked();

    void onBtSelectTimeClicked();

    void onBtnAcceptClicked();

    void saveReminder(String reminderName, int year, int month, int day, int hour, int minute);
}
