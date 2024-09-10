package com.chrisgalhur.tareapp.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.chrisgalhur.tareapp.entity.Reminder;

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
}
