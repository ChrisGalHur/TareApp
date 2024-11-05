package com.chrisgalhur.tareapp.model.interf;

import com.chrisgalhur.tareapp.entity.Reminder;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface FormReminderModel {

    Completable saveReminder(Reminder reminderToSave);

    Completable updateReminder(Reminder reminderToUpdate);

    Single<Reminder> getReminder(int reminderId);

    Single<Integer> deleteReminder(int reminderId);
}
