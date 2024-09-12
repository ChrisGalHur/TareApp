package com.chrisgalhur.tareapp.model;

import com.chrisgalhur.tareapp.entity.Reminder;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface FormReminderModel {

    Single<Reminder> getReminder(int reminderId);

    Completable saveReminder(Reminder reminderToSave);

    Single<Integer> deleteReminder(int reminderId);
}
