package com.chrisgalhur.tareapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.chrisgalhur.tareapp.ui.activity.MainActivity;
import com.chrisgalhur.tareapp.util.NotificationUtil;

public class ReminderNotificationClickReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtil.stopSound(context);
        NotificationUtil.stopVibrate(context);

        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(mainActivityIntent);
    }
}