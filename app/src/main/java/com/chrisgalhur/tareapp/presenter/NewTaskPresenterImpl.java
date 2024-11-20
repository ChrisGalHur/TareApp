package com.chrisgalhur.tareapp.presenter;

import android.content.Context;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.presenter.interf.NewTaskPresenter;
import com.chrisgalhur.tareapp.ui.activity.view.NewTaskView;

import java.util.Arrays;
import java.util.List;

public class NewTaskPresenterImpl implements NewTaskPresenter {

    private final NewTaskView view;
    private final Context context;

    //region CONSTRUCTOR
    public NewTaskPresenterImpl(NewTaskView view, Context context){
        this.view = view;
        this.context = context;
    }
    //endregion CONSTRUCTOR

    //region LOAD_TASKS
    @Override
    public void loadTasks() {
        // Recoger recordatorio de string.xml
        List<String> tasks = Arrays.asList(context.getString(R.string.reminder));
        view.updateTasks(tasks);
    }

    @Override
    public void dismissDialog() {
        view.dismissDialog();
    }
    //endregion LOAD_TASKS
}
