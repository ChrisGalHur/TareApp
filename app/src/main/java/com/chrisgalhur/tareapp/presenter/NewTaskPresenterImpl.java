package com.chrisgalhur.tareapp.presenter;

import com.chrisgalhur.tareapp.presenter.interf.NewTaskPresenter;
import com.chrisgalhur.tareapp.ui.activity.view.NewTaskView;

import java.util.Arrays;
import java.util.List;

public class NewTaskPresenterImpl implements NewTaskPresenter {

    //region INJECTION
    private final NewTaskView view;
    //endregion INJECTION

    //region CONSTRUCTOR
    public NewTaskPresenterImpl(NewTaskView view){
        this.view = view;
    }
    //endregion CONSTRUCTOR

    //region LOAD_TASKS
    @Override
    public void loadTasks() {
        List<String> tasks = Arrays.asList("Recordatorio");
        view.updateTasks(tasks);
    }
    //endregion LOAD_TASKS

    //region ADD_TASK
    @Override
    public void addTask(String taskName) {

    }
    //endregion ADD_TASK
}
