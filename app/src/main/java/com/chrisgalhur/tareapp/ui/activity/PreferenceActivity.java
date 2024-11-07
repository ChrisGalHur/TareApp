package com.chrisgalhur.tareapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.presenter.PreferencePresenterImpl;
import com.chrisgalhur.tareapp.presenter.interf.PreferencePresenter;
import com.chrisgalhur.tareapp.util.BaseActivity;
import com.chrisgalhur.tareapp.view.PreferenceView;

public class PreferenceActivity extends BaseActivity implements PreferenceView {

    private ImageView ivBack;
    private PreferencePresenter presenter;
    private ConstraintLayout languageLayout;
    private ConstraintLayout permissionsLayout;
    private ConstraintLayout aboutLayout;

    //region LIFECYCLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_preference);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        presenter = new PreferencePresenterImpl(this);

        ivBack = findViewById(R.id.ivBackPreference);
        languageLayout = findViewById(R.id.constLayLanguagePreference);
        permissionsLayout = findViewById(R.id.constLayPermissionPreference);
        aboutLayout = findViewById(R.id.constLayAboutAppPreference);

        ivBack.setOnClickListener(v -> finish());
        languageLayout.setOnClickListener(v -> presenter.onLanguageClicked());
        permissionsLayout.setOnClickListener(v -> presenter.onPermissionsClicked());
        aboutLayout.setOnClickListener(v -> presenter.onAboutClicked());
    }
    //endregion LIFECYCLE

    //region UI METHODS
    @Override
    public void navigateToPermissions() {
        Intent intent = new Intent(this, PermissionActivity.class);
        startActivity(intent);
    }
    //endregion UI METHODS
}