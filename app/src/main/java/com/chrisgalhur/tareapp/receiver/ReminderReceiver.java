package com.chrisgalhur.tareapp.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.chrisgalhur.tareapp.util.NotificationUtil;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Obtain the task data from the intent
        String reminderName = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");

        // Create intent to silence the alarm
        Intent silentIntent = new Intent(context, SilentAlarmReceiver.class);
        PendingIntent silentPendingIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            silentPendingIntent = PendingIntent.getBroadcast(context, 0, silentIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        } else {
            silentPendingIntent = PendingIntent.getBroadcast(context, 0, silentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        // Show the notification and play the sound
        NotificationUtil.showNotification(context, reminderName, description, silentPendingIntent);
        NotificationUtil.playSound(context);
        NotificationUtil.vibrate(context);
    }
}
