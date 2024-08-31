package com.chrisgalhur.tareapp.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.presenter.NewTaskPresenter;
import com.chrisgalhur.tareapp.presenter.NewTaskPresenterImpl;
import com.chrisgalhur.tareapp.view.NewTaskView;

import java.util.List;

public class NewTaskDialogFragment extends DialogFragment implements NewTaskView {

    //region INJECTION
    private NewTaskPresenter presenter;
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    //endregion INJECTION

    //region ON_CREATE_VIEW
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("'/'/ NEW TASK DIALOG", "onCreateView");

        View view = inflater.inflate(R.layout.dialog_new_task, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNewTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter = new NewTaskPresenterImpl(this);
        presenter.loadTasks();

        return view;
    }
    //endregion ON_CREATE_VIEW

    //region UPDATE_TASKS
    @Override
    public void updateTasks(List<String> tasks) {
        if(tasks.isEmpty()){
            Log.d("'/'/ NEW TASK DIALOG", "updateTasks: tasks is empty");
        }else{
            Log.d("'/'/ NEW TASK DIALOG", "tasks: " + tasks.size());
        }
        adapter = new TaskAdapter(tasks, presenter);
        recyclerView.setAdapter(adapter);
    }
    //endregion UPDATE_TASKS

    //region ON_RESUME
    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //margin top
            params.y = 100;
            params.gravity = Gravity.CENTER_VERTICAL;
            params.horizontalMargin = 0;
            params.verticalMargin = 0;
            window.setAttributes(params);
        }
    }
    //endregion ON_RESUME

    //region ON_CREATE_DIALOG
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
    //endregion ON_CREATE_DIALOG
}