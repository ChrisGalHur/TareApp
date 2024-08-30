package com.chrisgalhur.tareapp.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    //region ON_START
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }
    }
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

}
