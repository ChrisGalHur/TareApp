package com.chrisgalhur.tareapp.ui.activity;

import android.Manifest;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.database.AppDatabase;
import com.chrisgalhur.tareapp.database.DatabaseConstants;
import com.chrisgalhur.tareapp.ui.dialog.NewTaskDialogFragment;
import com.chrisgalhur.tareapp.entity.Reminder;
import com.chrisgalhur.tareapp.preference.MyPreferences;
import com.chrisgalhur.tareapp.presenter.MainPresenterImpl;
import com.chrisgalhur.tareapp.presenter.interf.MainPresenter;
import com.chrisgalhur.tareapp.ui.adapter.ReminderAdapter;
import com.chrisgalhur.tareapp.util.BaseActivity;
import com.chrisgalhur.tareapp.util.NotificationUtil;
import com.chrisgalhur.tareapp.ui.activity.view.MainView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends BaseActivity implements MainView {

    //region CONSTANTS & STATIC PROPERTIES
    private static final String TAG = "'/'/ MainActivity";
    private static final int REQUEST_CODE_SCHEDULE_EXACT_ALARM = 1;
    //endregion CONSTANTS & STATIC PROPERTIES

    private ActivityResultLauncher<String> requestPermissionLauncher;
    private RecyclerView recyclerViewReminders;
    private ReminderAdapter reminderAdapter;
    private AppDatabase db;
    private MyPreferences preferences;
    private MainPresenter presenter;
    private ImageView ivPreference;
    private ImageView ivNewTask;

    //region LIFECYCLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //ACTIVITY WORKING
        //startActivity(new Intent(MainActivity.this, PreferenceActivity.class));

        // PERMISSIONS
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                Log.d(TAG, "Permission granted for exact alarm scheduling");
            } else {
                Log.d(TAG, "Permission for exact alarm not granted");
                showPermissionRationale();
            }
        });

        requestPermission();

        NotificationUtil.createNotificationChannel(this);

        recyclerViewReminders = findViewById(R.id.recyclerViewRemindersMain);
        recyclerViewReminders.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DatabaseConstants.DATABASE_NAME).build();
        loadReminders();

        preferences = new MyPreferences(this);
        presenter = new MainPresenterImpl(this);

        if (preferences.isFirstTimeLaunch()) {
            launchOnboardingActivity();
            finish();
        }

        ivPreference = findViewById(R.id.ivPreferenceMain);
        ivNewTask = findViewById(R.id.ivNewTaskMain);

        ivPreference.setOnClickListener(v -> presenter.onBtnToPreferencesClicked());

        ivNewTask.setOnClickListener(v -> presenter.onBtnNewTaskClicked());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadReminders();
    }
    //endregion LIFECYCLE

    //region UI METHODS
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void navigateToPreferences() {
        Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
        startActivity(intent);
    }

    @Override
    public void openNewTaskDialog() {
        NewTaskDialogFragment dialog = new NewTaskDialogFragment();
        dialog.show(getSupportFragmentManager(), "NewTaskDialogFragment");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_SCHEDULE_EXACT_ALARM) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permission granted for exact alarm scheduling");
            } else {
                // Permiso denegado
                showPermissionRationale();
            }
        }
    }
    //endregion UI METHODS

    //region PERMISSION METHODS
    private void showPermissionRationale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission required")
                    .setMessage("To schedule alarms and reminders, we need your permission. " +
                            "Follow this steps:\n\n1. Roll window, click on Alarms & Reminders. \n2. Click on Enable permission. \n3. Go back to the app.")
                    .setPositiveButton("OK", (dialog, which) -> {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> {
                        Snackbar.make(findViewById(android.R.id.content), "Without the permission, we don't schedule alarms and reminders", Snackbar.LENGTH_LONG).show();
                        dialog.dismiss();
                    })
                    .show();
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null && !alarmManager.canScheduleExactAlarms()) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SCHEDULE_EXACT_ALARM)) {
                    showPermissionRationale();
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.SCHEDULE_EXACT_ALARM);
                    Log.d(TAG, "Requesting permission for exact alarm scheduling");
                }
            } else {
                Log.d(TAG, "Permission already granted for exact alarm scheduling");
            }
        }
    }
    //endregion PERMISSION METHODS

    //region AUXILIARY PRIVATE METHODS
    private void launchOnboardingActivity() {
        preferences.setFirstTimeLaunch(false);
        startActivity(new Intent(MainActivity.this, OnboardingActivity.class));
    }

    private void loadReminders() {
        new Thread(() -> {
            try {
                List<Reminder> reminders = db.reminderDao().getAll();
                Log.d(TAG, "Reminders loaded: " + reminders.size());
                runOnUiThread(() -> {
                    reminderAdapter = new ReminderAdapter(reminders, position -> presenter.onReminderClicked(reminders.get(position).getId()));
                    recyclerViewReminders.setAdapter(reminderAdapter);
                });
            } catch (Exception e) {
                Log.e(TAG, "Error loading reminders", e);
            }
        }).start();
    }
    //endregion AUXILIARY PRIVATE METHODS
}