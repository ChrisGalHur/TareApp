package com.chrisgalhur.tareapp.presenter;

import android.content.Context;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.entity.UserPreferences;
import com.chrisgalhur.tareapp.presenter.interf.PreferencePresenter;
import com.chrisgalhur.tareapp.ui.activity.view.PreferenceView;

import java.util.Arrays;

public class PreferencePresenterImpl implements PreferencePresenter {

    private final PreferenceView view;
    private final UserPreferences userPreferences;
    private final Context context;

    public PreferencePresenterImpl(PreferenceView view, Context context) {
        this.view = view;
        this.context = context;
        userPreferences = new UserPreferences(context);
    }

    @Override
    public void onPermissionsClicked() {
        view.navigateToPermissions();
    }

    @Override
    public void loadLanguagePreference() {
        String currentLanguage = userPreferences.getLanguage();
        String[] languageCodes = context.getResources().getStringArray(R.array.language_values);
        String[] languageOptions = context.getResources().getStringArray(R.array.language_options);
        
        int position = Arrays.asList(languageCodes).indexOf(currentLanguage);
        if (position == -1) {
            position = 0; // Si no se encuentra, posición predeterminada
        }


        view.updateLanguageSelection(position);
        view.setSpinnerPrompt(languageOptions[position]);
    }

    @Override
    public void saveLanguagePreference(String selectedLanguage) {
        userPreferences.setLanguage(selectedLanguage);
    }

    @Override
    public void applyLanguage(String selectedLanguage) {
        userPreferences.applySavedLocale(context);
    }

    @Override
    public void onAboutClicked() {
        //todo
    }
}
