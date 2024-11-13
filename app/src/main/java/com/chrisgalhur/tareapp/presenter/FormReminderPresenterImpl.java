package com.chrisgalhur.tareapp.presenter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.entity.Reminder;
import com.chrisgalhur.tareapp.model.interf.FormReminderModel;
import com.chrisgalhur.tareapp.presenter.interf.FormReminderPresenter;
import com.chrisgalhur.tareapp.receiver.ReminderReceiver;
import com.chrisgalhur.tareapp.ui.activity.view.FormReminderView;

import java.time.LocalDateTime;
import java.util.Calendar;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FormReminderPresenterImpl implements FormReminderPresenter {

    //region INJECTION
    private final FormReminderView view;
    private final FormReminderModel model;
    private final CompositeDisposable disposables = new CompositeDisposable();
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
        view.sendReminderSave();
    }
    //endregion ON_BT_SELECT_DATE_CLICKED

    //region SAVE_REMINDER
    @Override
    public void saveReminder(String reminderName, String description, boolean completed, int year, int month, int day, int hour, int minute) {
        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        Reminder reminder = new Reminder(reminderName, description, completed,date);
        Disposable disposable = model.saveReminder(reminder)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.showSuccess(view.getContext().getString(R.string.success_reminder_saved)),
                        throwable -> view.showError(throwable.getMessage())
                );
        disposables.add(disposable);
        setReminderAlarm(reminder);
    }
    //endregion SAVE_REMINDER

    //region UPDATE_REMINDER
    @Override
    public void updateReminder(int reminderId, String reminderName, String description, boolean completed, int year, int month, int day, int hour, int minute) {
        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);
        Reminder reminder = new Reminder(reminderId, reminderName, description, completed, date);
        Disposable disposable = model.updateReminder(reminder)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.showSuccess(view.getContext().getString(R.string.success_reminder_updated)),
                        throwable -> view.showError(throwable.getMessage())
                );
        disposables.add(disposable);
        setReminderAlarm(reminder);
    }
    //endregion UPDATE_REMINDER

    //region SET_REMINDER_ALARM
    @Override
    public void setReminderAlarm(Reminder reminder) {
        // todo: refactor this method
        Context context = view.getContext();
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderReceiver.class);
        intent.putExtra("title", reminder.getName());
        intent.putExtra("description", reminder.getDescription());

        PendingIntent pendingIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getBroadcast(context, reminder.getId(), intent, PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getBroadcast(context, reminder.getId(), intent, 0);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(reminder.getReminderDate().getYear(), reminder.getReminderDate().getMonthValue() - 1, reminder.getReminderDate().getDayOfMonth(),
                reminder.getReminderDate().getHour(), reminder.getReminderDate().getMinute(), 0);

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    }
    //endregion SET_REMINDER_ALARM

    //region LOAD_REMINDER
    @Override
    public void loadReminder(int reminderId) {
        Disposable disposable = model.getReminder(reminderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        reminder -> {
                            if (reminder != null) {
                                view.loadReminder(reminder);
                            } else {
                                view.showError(view.getContext().getString(R.string.error_reminder_not_found));
                            }
                        },
                        throwable -> view.showError(throwable.getMessage())
                );
        disposables.add(disposable);
    }
    //endregion LOAD_REMINDER

    //region ON_BT_DELETE_REMINDER_CLICKED
    @Override
    public void onBtDeleteReminderClicked(int reminderId) {
        Disposable disposable = model.deleteReminder(reminderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        rowsDeleted -> {
                            if (rowsDeleted > 0) {
                                view.showSuccess(view.getContext().getString(R.string.success_reminder_deleted));
                            } else {
                                view.showError(view.getContext().getString(R.string.error_reminder_not_found));
                            }
                        },
                        throwable -> view.showError(throwable.getMessage())
                );
        disposables.add(disposable);
    }
    //endregion ON_BT_DELETE_REMINDER_CLICKED

    //region CLEAR_DISPOSABLES
    @Override
    public void clearDisposables() {
        disposables.clear();
    }
    //endregion CLEAR_DISPOSABLES
}
