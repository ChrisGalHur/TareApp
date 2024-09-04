package com.chrisgalhur.tareapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.chrisgalhur.tareapp.dao.ReminderDao;
import com.chrisgalhur.tareapp.entity.Reminder;

@Database(entities = {Reminder.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReminderDao reminderDao();
}
