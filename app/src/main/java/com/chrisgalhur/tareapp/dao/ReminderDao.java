package com.chrisgalhur.tareapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.chrisgalhur.tareapp.entity.Reminder;

import java.util.List;

@Dao
public interface ReminderDao {

    @Insert
    void insert(Reminder reminder);

    @Query("SELECT * FROM Reminder")
    List<Reminder> getAll();
}
