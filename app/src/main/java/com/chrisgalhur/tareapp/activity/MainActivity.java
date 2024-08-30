package com.chrisgalhur.tareapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.dialog.NewTaskDialogFragment;
import com.chrisgalhur.tareapp.preference.MyPreferences;
import com.chrisgalhur.tareapp.presenter.MainPresenter;
import com.chrisgalhur.tareapp.presenter.MainPresenterImpl;
import com.chrisgalhur.tareapp.util.BaseActivity;
import com.chrisgalhur.tareapp.view.MainView;

public class MainActivity extends BaseActivity implements MainView {

    //region INJECTION
    private MyPreferences preferences;
    private MainPresenter presenter;
    //endregion INJECTION

    //region UI
    private Button btnToCalendar;
    private Button btToOnboarding;
    private ImageView ivPreference;
    private ImageView ivNewTask;
    //endregion UI

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

}