package com.chrisgalhur.tareapp.entity;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class UserPreferences {

    private static final String PREFS_NAME = "user_preferences";
    private static final String KEY_LANGUAGE = "language_code";
    private SharedPreferences sharedPreferences;

    public UserPreferences(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public String getLanguage() {
        return sharedPreferences.getString(KEY_LANGUAGE, "en");
    }

    public void setLanguage(String selectedLanguage) {
        sharedPreferences.edit().putString(KEY_LANGUAGE, selectedLanguage).apply();
    }

    public void applySavedLocale(Context context) {
        String languageCode = getLanguage();
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        android.content.res.Resources resources = context.getResources();
        android.content.res.Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
