package com.chrisgalhur.tareapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.database.AppDatabase;
import com.chrisgalhur.tareapp.database.DatabaseConstants;
import com.chrisgalhur.tareapp.dialog.NewTaskDialogFragment;
import com.chrisgalhur.tareapp.entity.Reminder;
import com.chrisgalhur.tareapp.preference.MyPreferences;
import com.chrisgalhur.tareapp.presenter.MainPresenter;
import com.chrisgalhur.tareapp.presenter.MainPresenterImpl;
import com.chrisgalhur.tareapp.util.BaseActivity;
import com.chrisgalhur.tareapp.ui.adapter.ReminderAdapter;
import com.chrisgalhur.tareapp.view.MainView;

import java.util.List;

public class MainActivity extends BaseActivity implements MainView {

    //region PROPERTIES
    private static final String TAG = "'/'/ MainActivity";
    private RecyclerView recyclerViewReminders;
    private ReminderAdapter reminderAdapter;
    private AppDatabase db;
    private MyPreferences preferences;
    private MainPresenter presenter;
    //endregion PROPERTIES

    //region UI
    private Button btnToCalendar;
    private Button btToOnboarding;
    private ImageView ivPreference;
    private ImageView ivNewTask;
    //endregion UI

    //region GET_CONTEXT
    @Override
    public Context getContext() {
        return this;
    }
    //endregion GET_CONTEXT

    //region ON_CREATE
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
        //startActivity(new Intent(MainActivity.this, FormReminderActivity.class));

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

        btnToCalendar = findViewById(R.id.btnToCalendarMain);
        btToOnboarding = findViewById(R.id.btnToOnboardingMain);
        ivPreference = findViewById(R.id.ivPreferenceMain);
        ivNewTask = findViewById(R.id.ivNewTaskMain);

        btnToCalendar.setOnClickListener(v -> presenter.onBtnToCalendarClicked());

        btToOnboarding.setOnClickListener(v -> launchOnboardingActivity());

        ivPreference.setOnClickListener(v -> presenter.onBtnToPreferencesClicked());

        ivNewTask.setOnClickListener(v -> presenter.onBtnNewTaskClicked());
    }
    //endregion ON_CREATE

    //region ON_RESUME
    @Override
    protected void onResume() {
        super.onResume();
        loadReminders();
    }
    //endregion ON_RESUME

    //region LAUNCH_ONBOARDING_ACTIVITY
    private void launchOnboardingActivity() {
        preferences.setFirstTimeLaunch(false);
        startActivity(new Intent(MainActivity.this, OnboardingActivity.class));
    }
    //endregion LAUNCH_ONBOARDING_ACTIVITY

    //region NAVIGATE_TO_CALENDAR
    @Override
    public void navigateToCalendar() {
        Intent intent = new Intent(MainActivity.this, CalendarActivity.class);
        startActivity(intent);
    }
    //endregion NAVIGATE_TO_CALENDAR

    //region NAVIGATE_TO_PREFERENCES
    @Override
    public void navigateToPreferences() {
        Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
        startActivity(intent);
    }
    //endregion NAVIGATE_TO_PREFERENCES

    //region OPEN_NEW_TASK_DIALOG
    @Override
    public void openNewTaskDialog() {
        NewTaskDialogFragment dialog = new NewTaskDialogFragment();
        dialog.show(getSupportFragmentManager(), "NewTaskDialogFragment");
    }
    //endregion OPEN_NEW_TASK_DIALOG

    //region LOAD_REMINDERS
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
    //endregion LOAD_REMINDERS
}