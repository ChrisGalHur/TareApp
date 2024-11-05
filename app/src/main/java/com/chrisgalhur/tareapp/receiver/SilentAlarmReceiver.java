package com.chrisgalhur.tareapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.chrisgalhur.tareapp.util.NotificationUtil;

public class SilentAlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtil.stopSound(context);
        NotificationUtil.stopVibrate(context);
    }
}