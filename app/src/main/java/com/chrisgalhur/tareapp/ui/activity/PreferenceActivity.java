package com.chrisgalhur.tareapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.presenter.interf.PreferencePresenter;
import com.chrisgalhur.tareapp.presenter.PreferencePresenterImpl;
import com.chrisgalhur.tareapp.view.PreferenceView;

public class PreferenceActivity extends AppCompatActivity implements PreferenceView {

    //region UI
    private ImageView ivBack;
    private PreferencePresenter presenter;
    //endregion UI

    //region ON_CREATE
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

        ivBack.setOnClickListener(v -> presenter.onBackClicked());
    }
    //endregion ON_CREATE

    //region NAVIGATE_TO_MAIN
    @Override
    public void navigateToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    //endregion NAVIGATE_TO_MAIN
}