package com.chrisgalhur.tareapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.presenter.PreferencePresenterImpl;
import com.chrisgalhur.tareapp.presenter.interf.PreferencePresenter;
import com.chrisgalhur.tareapp.ui.activity.view.PreferenceView;
import com.chrisgalhur.tareapp.util.BaseActivity;

public class PreferenceActivity extends BaseActivity implements PreferenceView {

    private PreferencePresenter presenter;
    private ImageView ivBack;
    private ConstraintLayout permissionsLayout;
    private ConstraintLayout aboutLayout;
    private Spinner spinnerLanguage;

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

        presenter = new PreferencePresenterImpl(this, this);

        ivBack = findViewById(R.id.ivBackPreference);
        permissionsLayout = findViewById(R.id.constLayPermissionPreference);
        aboutLayout = findViewById(R.id.constLayAboutAppPreference);
        spinnerLanguage = findViewById(R.id.spinnerLanguagePreference);

        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languageCodes = getResources().getStringArray(R.array.language_values);
                String selectedLanguage = languageCodes[position];
                if (!selectedLanguage.equals(presenter.getLanguagePreference())) {
                    presenter.saveLanguagePreference(selectedLanguage);
                    presenter.applyLanguage(selectedLanguage);
                    recreate(); // Recrea la actividad para aplicar el idioma seleccionado
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ivBack.setOnClickListener(v -> presenter.onBackClicked());
        permissionsLayout.setOnClickListener(v -> presenter.onPermissionsClicked());
        aboutLayout.setOnClickListener(v -> presenter.onAboutClicked());
        presenter.loadLanguagePreference();
    }
    //endregion LIFECYCLE

    //region UI METHODS
    @Override
    public void navigateToPermissions() {
        Intent intent = new Intent(this, PermissionActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateLanguageSelection(int position) {
        spinnerLanguage.setSelection(position);
    }

    @Override
    public void setSpinnerPrompt(String prompt) {
        spinnerLanguage.setPrompt(prompt);
    }

    @Override
    public void navigateBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToAbout() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/ChrisGalHur"));
        startActivity(intent);
    }
    //endregion UI METHODS
}