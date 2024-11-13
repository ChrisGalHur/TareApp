package com.chrisgalhur.tareapp.ui.activity.view;

public interface PreferenceView {

    void navigateToPermissions();
    void updateLanguageSelection(int position);
    void setSpinnerPrompt(String prompt);
    void navigateBack();
    void navigateToAbout();
}
