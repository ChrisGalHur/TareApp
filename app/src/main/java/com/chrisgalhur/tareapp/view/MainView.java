package com.chrisgalhur.tareapp.view;

import android.content.Context;

public interface MainView {
    Context getContext();
    void navigateToCalendar();
    void navigateToPreferences();
    void openNewTaskDialog();
    void navigateToReminder(int reminderId);
}
