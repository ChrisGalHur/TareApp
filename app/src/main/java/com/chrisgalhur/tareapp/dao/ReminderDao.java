package com.chrisgalhur.tareapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.chrisgalhur.tareapp.database.DatabaseConstants;
import com.chrisgalhur.tareapp.entity.Reminder;

import java.util.List;

@Dao
public interface ReminderDao {

    @Insert
    void insert(Reminder reminder);

    @Query("UPDATE " + DatabaseConstants.TABLE_REMINDER + " SET " + DatabaseConstants.COLUMN_TASK_NAME + " = :name, " + DatabaseConstants.COLUMN_TASK_DESCRIPTION + " = :description, " + DatabaseConstants.COLUMN_REMINDER_DATE + " = :date WHERE " + DatabaseConstants.COLUMN_TASK_ID + " = :taskId")
    void updateReminder(int taskId, String name, String description, long date);
    @Query("SELECT * FROM " + DatabaseConstants.TABLE_REMINDER)
    List<Reminder> getAll();

    @Query("SELECT * FROM " + DatabaseConstants.TABLE_REMINDER + " WHERE " + DatabaseConstants.COLUMN_TASK_ID + " = :reminderId")
    Reminder getReminder(int reminderId);

    @Query("DELETE FROM " + DatabaseConstants.TABLE_REMINDER + " WHERE " + DatabaseConstants.COLUMN_TASK_ID + " = :reminderId")
    int deleteById(int reminderId);
}
