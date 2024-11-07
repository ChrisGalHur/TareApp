package com.chrisgalhur.tareapp.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
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
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
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
        Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }
}
