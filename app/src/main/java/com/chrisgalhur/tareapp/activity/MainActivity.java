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
import com.chrisgalhur.tareapp.preference.MyPreferences;
import com.chrisgalhur.tareapp.util.BaseActivity;

public class MainActivity extends BaseActivity {

    //region INJECTION
    private MyPreferences preferences;
    //endregion INJECTION

    //region UI
    private Button btnToCalendar;
    private Button btToOnboarding;
    private ImageView ivPreference;
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

        if (preferences.isFirstTimeLaunch()) {
            launchOnboardingActivity();
            finish();
        }

        btnToCalendar = findViewById(R.id.btnToCalendarMain);
        btToOnboarding = findViewById(R.id.btnToOnboardingMain);
        ivPreference = findViewById(R.id.ivPreferenceMain);

        btnToCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });

        btToOnboarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchOnboardingActivity();
            }
        });

        ivPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PreferenceActivity.class));
            }
        });
    }
    //endregion ON_CREATE

    //region LAUNCH_ONBOARDING_ACTIVITY
    private void launchOnboardingActivity() {
        preferences.setFirstTimeLaunch(false);
        startActivity(new Intent(MainActivity.this, OnboardingActivity.class));
    }
    //endregion LAUNCH_ONBOARDING_ACTIVITY
}