package com.chrisgalhur.tareapp.presenter;

import com.chrisgalhur.tareapp.presenter.interf.CalendarPresenter;
import com.chrisgalhur.tareapp.ui.activity.view.CalendarView;

public class CalendarPresenterImpl implements CalendarPresenter {

    //region INJECTION
    private CalendarView view;
    //endregion INJECTION

    //region CONSTRUCTOR
    public CalendarPresenterImpl(CalendarView view) {
        this.view = view;
    }
    //endregion CONSTRUCTOR
}
