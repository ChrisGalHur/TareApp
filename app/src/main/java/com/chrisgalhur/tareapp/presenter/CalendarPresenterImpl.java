package com.chrisgalhur.tareapp.presenter;

import com.chrisgalhur.tareapp.view.CalendarView;

public class CalendarPresenterImpl implements CalendarPresenter{

    //region INJECTION
    private CalendarView view;
    //endregion INJECTION

    //region CONSTRUCTOR
    public CalendarPresenterImpl(CalendarView view) {
        this.view = view;
    }
    //endregion CONSTRUCTOR
}
