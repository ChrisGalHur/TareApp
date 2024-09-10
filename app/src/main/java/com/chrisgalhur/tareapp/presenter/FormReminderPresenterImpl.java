package com.chrisgalhur.tareapp.presenter;

import com.chrisgalhur.tareapp.entity.Reminder;
import com.chrisgalhur.tareapp.model.FormReminderModel;
import com.chrisgalhur.tareapp.view.FormReminderView;

import java.time.LocalDateTime;

public class FormReminderPresenterImpl implements FormReminderPresenter {

    //region INJECTION
    private final FormReminderView view;
    private final FormReminderModel model;
    //endregion INJECTION

    //region CONSTRUCTOR
    public FormReminderPresenterImpl(FormReminderView view, FormReminderModel model) {
        this.view = view;
        this.model = model;
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
        view.sendReminder();
    }

    @Override
    public void saveReminder(String reminderName, String description, boolean completed, int year, int month, int day, int hour, int minute) {
        //LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        Reminder reminder = new Reminder(reminderName, description, completed,date);
        model.saveReminder(reminder);
    }
    //endregion ON_BT_SELECT_DATE_CLICKED
}
