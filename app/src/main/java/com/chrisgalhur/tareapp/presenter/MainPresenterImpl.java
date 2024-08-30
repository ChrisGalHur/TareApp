package com.chrisgalhur.tareapp.presenter;

import android.view.View;

import com.chrisgalhur.tareapp.view.MainView;

public class MainPresenterImpl implements MainPresenter{

    //region INJECTION
    private final MainView view;
    //endregion INJECTION

    //region CONSTRUCTOR
    public MainPresenterImpl(MainView view){
        this.view = view;
    }
    //endregion CONSTRUCTOR

    //region ON_BTN_TO_CALENDAR_CLICKED
    @Override
    public void onBtnToCalendarClicked() {
        view.navigateToCalendar();
    }
    //endregion ON_BTN_TO_CALENDAR_CLICKED

    //region ON_BTN_TO_PREFERENCES_CLICKED
    @Override
    public void onBtnToPreferencesClicked() {
        view.navigateToPreferences();
    }
    //endregion ON_BTN_TO_PREFERENCES_CLICKED

    //region ON_BTN_NEW_TASK_CLICKED
    @Override
    public void onBtnNewTaskClicked() {
        view.openNewTaskDialog();
    }
    //endregion ON_BTN_NEW_TASK_CLICKED

}
