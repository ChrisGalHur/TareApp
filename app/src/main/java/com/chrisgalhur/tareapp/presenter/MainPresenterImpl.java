package com.chrisgalhur.tareapp.presenter;

import android.content.Intent;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.ui.activity.FormReminderActivity;
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

    //region ON_REMINDER_CLICKED
    @Override
    public void onReminderClicked(int reminderId) {
        Intent intent = new Intent(view.getContext(), FormReminderActivity.class);
        intent.putExtra(view.getContext().getString(R.string.extra_reminder_id), reminderId);
        view.getContext().startActivity(intent);
    }
    //endregion ON_REMINDER_CLICKED
}
