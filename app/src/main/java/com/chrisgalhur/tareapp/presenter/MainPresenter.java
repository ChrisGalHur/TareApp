package com.chrisgalhur.tareapp.presenter;

public interface MainPresenter {
    void onBtnToCalendarClicked();
    void onBtnToPreferencesClicked();
    void onBtnNewTaskClicked();
    void onReminderClicked(int reminderId);
}
