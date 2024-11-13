package com.chrisgalhur.tareapp.ui.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.util.BaseActivity;
import com.chrisgalhur.tareapp.util.NotificationUtil;

public class StopAlarmActivity extends BaseActivity {

    //region CONSTANTS & STATIC PROPERTIES
    private static final String TAG = "'/'/ StopAlarmActivity";
    //endregion CONSTANTS & STATIC PROPERTIES

    //region INSTANCE PROPERTIES
    //endregion INSTANCE PROPERTIES

    //region ACTIVITY LIFECYCLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stop_alarm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG, "Activity started to stop alarm");

        //Stop the alarm
        NotificationUtil.stopSound(this);
        NotificationUtil.stopVibrate(this);

        //Set button to close the activity
    }
    //endregion ACTIVITY LIFECYCLE
}