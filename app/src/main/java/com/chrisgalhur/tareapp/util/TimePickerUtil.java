package com.chrisgalhur.tareapp.util;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.TimePicker;

import com.chrisgalhur.tareapp.R;

import java.util.Calendar;

public class TimePickerUtil {

    public interface TimePickerListener {
        void onTimeSelected(int hour, int minute);
    }

    public static void openTimePicker(Context context, TimePickerListener listener) {
        //Obtain the current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        //Create instance of TimePickerDialog and show it
        TimePickerDialog timePickerDialog = new TimePickerDialog(
            new ContextThemeWrapper(context, R.style.CustomTimePicker),
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    listener.onTimeSelected(hourOfDay, minute);
                }
            }, hour, minute, true);
        timePickerDialog.show();
    }
}
