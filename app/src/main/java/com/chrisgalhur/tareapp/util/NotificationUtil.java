package com.chrisgalhur.tareapp.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.receiver.ReminderMuteReceiver;
import com.chrisgalhur.tareapp.receiver.ReminderNotificationClickReceiver;

public class NotificationUtil {

    public static final int ALARM_NOTIFICATION_ID = 1;
    private static final String TAG = "'/'/ NotificationUtil";
    private static final String CHANNEL_ID = "alarm_channel";
    private static MediaPlayer mediaPlayer;

    public static void createNotificationChannel(Context context) {
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);

        NotificationChannel existingChannel = notificationManager.getNotificationChannel(CHANNEL_ID);
        if (existingChannel != null) {
            notificationManager.deleteNotificationChannel(CHANNEL_ID);
        }

        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Reminder", importance);
        channel.setDescription("Reminder notification channel");
        channel.enableVibration(true);
        channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        channel.setSound(null, null);

        notificationManager.createNotificationChannel(channel);
    }

    public static void showNotification(Context context, String title, String description, PendingIntent dismissPendingIntent) {
        // Intent para abrir la app al hacer click en la notificación
        Intent clickIntent = new Intent(context, ReminderNotificationClickReceiver.class);
        PendingIntent clickPendingIntent = PendingIntent.getActivity(context, ALARM_NOTIFICATION_ID, clickIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        //Intent para silenciar la alarma al hacer click en el botón de la notificación
        Intent muteIntent = new Intent(context, ReminderMuteReceiver.class);
        PendingIntent mutePendingIntent = PendingIntent.getBroadcast(context, 0, muteIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.logo_pers_no_background)
                //.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.logo_pers_no_background)) // Ícono grande para la notificación
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setContentIntent(clickPendingIntent)
                .addAction(0, context.getString(R.string.mute), mutePendingIntent)
                .setSound(null)
                .setColor(context.getResources().getColor(R.color.light_blue, null));

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(ALARM_NOTIFICATION_ID, builder.build());
    }

    public static void playSound(Context context) {
        stopSound(context);

        try{
            Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.star_wars);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(context, uri);
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build());
            mediaPlayer.setLooping(true); // Repetir el sonido como una alarma
            mediaPlayer.prepare();
            mediaPlayer.start();
        }catch (Exception e){
            Log.e(TAG, "Error playing sound", e);
        }
    }

    public static void stopSound(Context context) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public static void vibrate(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if(vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

    public static void stopVibrate(Context context) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if(vibrator != null) {
            vibrator.cancel();
        }
    }
}
