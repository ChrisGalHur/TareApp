package com.chrisgalhur.tareapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.chrisgalhur.tareapp.dao.ReminderDao;
import com.chrisgalhur.tareapp.entity.Reminder;
import com.chrisgalhur.tareapp.util.Converters;

@Database(entities = {Reminder.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReminderDao reminderDao();
}
