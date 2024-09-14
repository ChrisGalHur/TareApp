package com.chrisgalhur.tareapp.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;

import com.chrisgalhur.tareapp.R;

import java.util.Calendar;

public class DatePickerUtil {

    public interface DatePickerListener {
        void onDateSelected(int year, int month, int day);
    }

    public static void openDatePicker(Context context, DatePickerListener listener) {
        //Obtain the current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //Crerate instance of DatePickerDialog and show it
        DatePickerDialog datePickerDialog = new DatePickerDialog(
            new ContextThemeWrapper(context, R.style.CustomDatePicker),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        listener.onDateSelected(year, month, day);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}
