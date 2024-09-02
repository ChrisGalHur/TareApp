package com.chrisgalhur.tareapp.presenter;

import com.chrisgalhur.tareapp.view.FormReminderView;

public class FormReminderPresenterImpl implements FormReminderPresenter {

    //region INJECTION
    private final FormReminderView view;
    //endregion INJECTION

    //region CONSTRUCTOR
    public FormReminderPresenterImpl(FormReminderView view) {
        this.view = view;
    }
    //endregion CONSTRUCTOR

    //region ON_BT_SELECT_DATE_CLICKED
    @Override
    public void onBtSelectDateClicked() {
        view.openDatePicker();
    }

    @Override
    public void onBtSelectTimeClicked() {
        view.openTimePicker();
    }

    @Override
    public void onBtnAcceptClicked() {
        view.saveReminder();
    }
    //endregion ON_BT_SELECT_DATE_CLICKED
}
