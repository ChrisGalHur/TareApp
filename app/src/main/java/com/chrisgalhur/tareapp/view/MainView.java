package com.chrisgalhur.tareapp.view;

import android.content.Context;

public interface MainView {
    Context getContext();
    void navigateToPreferences();
    void openNewTaskDialog();
}
