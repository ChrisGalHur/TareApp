package com.chrisgalhur.tareapp.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.chrisgalhur.tareapp.util.NotificationUtil;

public class ReminderMuteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtil.stopSound(context);
        NotificationUtil.stopVibrate(context);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(NotificationUtil.ALARM_NOTIFICATION_ID);
        }
    }
}
