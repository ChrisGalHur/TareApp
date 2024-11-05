package com.chrisgalhur.tareapp.presenter;

import com.chrisgalhur.tareapp.presenter.interf.PreferencePresenter;
import com.chrisgalhur.tareapp.view.PreferenceView;

public class PreferencePresenterImpl implements PreferencePresenter {

    //region INJECTION
    private final PreferenceView view;
    //endregion INJECTION

    //region CONSTRUCTOR
    public PreferencePresenterImpl(PreferenceView view) {
        this.view = view;
    }
    //endregion CONSTRUCTOR

    //region ON_BACK_CLICKED
    @Override
    public void onBackClicked() {
        view.navigateToMain();
    }
    //endregion ON_BACK_CLICKED
}
