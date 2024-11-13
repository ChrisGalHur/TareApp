package com.chrisgalhur.tareapp.ui.activity.view;

import android.content.Context;

public interface MainView {
    Context getContext();
    void navigateToPreferences();
    void openNewTaskDialog();
}
