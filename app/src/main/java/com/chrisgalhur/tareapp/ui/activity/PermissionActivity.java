package com.chrisgalhur.tareapp.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.entity.Permission;
import com.chrisgalhur.tareapp.presenter.PermissionPresenterImpl;
import com.chrisgalhur.tareapp.presenter.interf.PermissionPresenter;
import com.chrisgalhur.tareapp.ui.adapter.PermissionAdapter;
import com.chrisgalhur.tareapp.util.BaseActivity;
import com.chrisgalhur.tareapp.ui.activity.view.PermissionView;

import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends BaseActivity implements PermissionView {

    private List<Permission> permissions = new ArrayList<>();
    private PermissionAdapter permissionAdapter;
    private PermissionPresenter presenter;
    private ImageView ivBack;

    //region LIFE CYCLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_permission);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        permissions.add(new Permission(getString(R.string.permission_post_notifications), getString(R.string.permission_post_notifications_description), "android.permission.POST_NOTIFICATIONS"));
        permissions.add(new Permission(getString(R.string.permission_schedule_exact_alarm), getString(R.string.permission_schedule_exact_alarm_description), "android.permission.SCHEDULE_EXACT_ALARM"));
        permissions.add(new Permission(getString(R.string.permission_ignore_battery_optimizations), getString(R.string.permission_ignore_battery_optimizations_description), "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"));

        RecyclerView recyclerViewPermissions = findViewById(R.id.RecyclerViewPermissionsPermissionItem);
        recyclerViewPermissions.setLayoutManager(new LinearLayoutManager(this));
        permissionAdapter = new PermissionAdapter(permissions, this);
        recyclerViewPermissions.setAdapter(permissionAdapter);

        presenter = new PermissionPresenterImpl(this, this, permissions);

        ivBack = findViewById(R.id.ivBackPermission);

        ivBack.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updatePermissionsStatus();
    }

    @Override
    public void refreshPermissionsList() {
        permissionAdapter.notifyDataSetChanged();
    }
}