package com.chrisgalhur.tareapp.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseReminder {

    private Context context;
    private static DatabaseReminder instance;
    private AppDatabase appDatabase;

    private DatabaseReminder(Context context) {
        this.context = context;
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "Reminder").build();
    }

    public static synchronized DatabaseReminder getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseReminder(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
