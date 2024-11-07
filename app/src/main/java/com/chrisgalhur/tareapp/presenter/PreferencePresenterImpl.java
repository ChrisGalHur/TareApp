package com.chrisgalhur.tareapp.presenter;

import com.chrisgalhur.tareapp.presenter.interf.PreferencePresenter;
import com.chrisgalhur.tareapp.view.PreferenceView;

public class PreferencePresenterImpl implements PreferencePresenter {

    private final PreferenceView view;

    public PreferencePresenterImpl(PreferenceView view) {
        this.view = view;
    }

    @Override
    public void onLanguageClicked() {
        //todo
    }

    @Override
    public void onPermissionsClicked() {
        view.navigateToPermissions();
    }

    @Override
    public void onAboutClicked() {
        //todo
    }
}
