package com.chrisgalhur.tareapp.util;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.chrisgalhur.tareapp.R;

public class PermissionHelper {

    private Context context;

    public PermissionHelper(Context context) {
        this.context = context;
    }

    public boolean isPermissionGranted(String permission) {
        if (permission.equals("android.permission.SCHEDULE_EXACT_ALARM")) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                return alarmManager.canScheduleExactAlarms();
            } else {
                return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
            }
        } else if (permission.equals("android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS")) {
            PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (powerManager != null) {
                return powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
            }
        } else {
            return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public void requestNotificationPermission(Activity activity) {
        try {
            Intent intent;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
                        .putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
            } else {
                intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        .setData(Uri.parse("package:" + context.getPackageName()));
            }
            activity.startActivityForResult(intent, 0);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, context.getString(R.string.configuration_screen_not_open) + "\n" +
                    context.getString(R.string.address_accept_notifications)  , Toast.LENGTH_SHORT).show();
        }
    }

    public void requestScheduleExactAlarmPermission(Activity context) {
        Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }

    public void requestIgnoreBatteryOptimizationsPermission(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
                intent.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Fallback: Show a message to the user or log the error
                Toast.makeText(context, "Esta acción no es compatible en este dispositivo.", Toast.LENGTH_LONG).show();
            }
        } else {
            // Manejo para versiones de Android inferiores a Marshmallow
            Toast.makeText(context, "La optimización de batería no aplica en versiones de Android inferiores a Marshmallow.", Toast.LENGTH_LONG).show();
        }
    }
}
