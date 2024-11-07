package com.chrisgalhur.tareapp.presenter;

import com.chrisgalhur.tareapp.presenter.interf.PermissionPresenter;
import com.chrisgalhur.tareapp.view.PermissionView;

public class PermissionPresenterImpl implements PermissionPresenter {

    private PermissionView view;

    public PermissionPresenterImpl(PermissionView view) {
        this.view = view;
    }
}
