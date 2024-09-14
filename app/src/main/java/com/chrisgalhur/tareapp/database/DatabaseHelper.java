package com.chrisgalhur.tareapp.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.chrisgalhur.tareapp.entity.Reminder;

import java.time.ZoneOffset;
import java.util.concurrent.Executors;

public class DatabaseHelper {

    //region PROPERTIES
    private static final String TAG = "'/'/ DatabaseHelper";
    private static DatabaseHelper instance;
    private AppDatabase appDatabase;
    //endregion PROPERTIES

    //region CONSTRUCTOR
    private DatabaseHelper(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DatabaseConstants.DATABASE_NAME)
                .build();
    }
    //endregion CONSTRUCTOR

    //region GET_INSTANCE
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }
    //endregion GET_INSTANCE

    //region GET_APP_DATABASE
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
    //endregion GET_APP_DATABASE

    //region SAVE_REMINDER
    public void saveReminder(Reminder reminder) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                appDatabase.reminderDao().insert(reminder);
                Log.d(TAG, "Reminder saved successfully " + reminder.getName());
            } catch (Exception e) {
                Log.e(TAG, "Error saving reminder " + reminder.getName(), e);
            }
        });
    }
    //endregion SAVE_REMINDER

    //region GET_REMINDER
    public Reminder getReminder(int reminderId) {
        return appDatabase.reminderDao().getReminder(reminderId);
    }
    //endregion GET_REMINDER

    //region UPDATE_REMINDER
    public void updateReminder(Reminder reminder) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                appDatabase.reminderDao().updateReminder(reminder.getId(), reminder.getName(), reminder.getDescription(), reminder.getReminderDate().toEpochSecond(ZoneOffset.UTC));
                Log.d(TAG, "Reminder updated successfully " + reminder.getName());
            } catch (Exception e) {
                Log.e(TAG, "Error updating reminder " + reminder.getName(), e);
            }
        });
    }
    //endregion UPDATE_REMINDER

    //region DELETE_REMINDER
    public int deleteReminder(int reminderId) {
        return appDatabase.reminderDao().deleteById(reminderId);
    }
    //endregion DELETE_REMINDER
}
