package com.chrisgalhur.tareapp.model;

import android.content.Context;

import com.chrisgalhur.tareapp.database.DatabaseHelper;
import com.chrisgalhur.tareapp.entity.Reminder;
import com.chrisgalhur.tareapp.model.interf.FormReminderModel;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class FormReminderModelImpl implements FormReminderModel {

    private final DatabaseHelper databaseHelper;

    //region CONSTRUCTOR
    public FormReminderModelImpl(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
    }
    //endregion CONSTRUCTOR

    //region SAVE_REMINDER
    @Override
    public Completable saveReminder(Reminder reminderToSave) {
        return Completable.fromAction(() -> databaseHelper.saveReminder(reminderToSave));
    }
    //endregion SAVE_REMINDER

    //region UPDATE_REMINDER
    @Override
    public Completable updateReminder(Reminder reminderToUpdate) {
        return Completable.fromAction(() -> databaseHelper.updateReminder(reminderToUpdate));
    }
    //endregion UPDATE_REMINDER

    //region GET_REMINDER
    @Override
    public Single<Reminder> getReminder(int reminderId) {
        return Single.fromCallable(() -> databaseHelper.getReminder(reminderId));
    }
    //endregion GET_REMINDER

    //region DELETE_REMINDER
    @Override
    public Single<Integer> deleteReminder(int reminderId) {
        return Single.fromCallable(() -> databaseHelper.deleteReminder(reminderId));
    }
    //endregion DELETE_REMINDER
}
