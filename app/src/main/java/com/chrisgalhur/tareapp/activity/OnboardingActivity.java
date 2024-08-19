package com.chrisgalhur.tareapp.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.util.MyViewPagerAdapter;

public class OnboardingActivity extends AppCompatActivity {

    //region INJECTION
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    //endregion INJECTION

    //region UI
    private Button btnSkip;
    //endregion UI

    //region ON_CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_onboarding);

        viewPager = findViewById(R.id.view_pager);
        btnSkip = findViewById(R.id.btn_skip);
    }
}
