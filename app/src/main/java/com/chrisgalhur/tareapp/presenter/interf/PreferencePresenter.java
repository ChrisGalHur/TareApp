package com.chrisgalhur.tareapp.presenter.interf;

public interface PreferencePresenter {

    void onPermissionsClicked();
    void onAboutClicked();
    void loadLanguagePreference();
    void saveLanguagePreference(String selectedLanguage);
    void applyLanguage(String selectedLanguage);
    String getLanguagePreference();
}
