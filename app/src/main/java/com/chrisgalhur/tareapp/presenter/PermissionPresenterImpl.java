package com.chrisgalhur.tareapp.presenter;

import android.content.Context;

import com.chrisgalhur.tareapp.entity.Permission;
import com.chrisgalhur.tareapp.presenter.interf.PermissionPresenter;
import com.chrisgalhur.tareapp.ui.activity.view.PermissionView;
import com.chrisgalhur.tareapp.util.PermissionHelper;

import java.util.List;

public class PermissionPresenterImpl implements PermissionPresenter {

    private PermissionView view;
    private PermissionHelper permissionHelper;
    private List<Permission> permissions;
    private Context context;

    public PermissionPresenterImpl(PermissionView view, Context context, List<Permission> permissions) {
        this.view = view;
        this.permissionHelper = new PermissionHelper(context);
        this.permissions = permissions;
    }

    @Override
    public void updatePermissionsStatus() {
        for (Permission permission : permissions) {
            boolean isGranted = permissionHelper.isPermissionGranted(permission.getRealName());
            permission.setGranted(isGranted);
        }
        view.refreshPermissionsList();
    }
}
