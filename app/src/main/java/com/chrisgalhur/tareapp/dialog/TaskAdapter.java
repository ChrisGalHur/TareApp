package com.chrisgalhur.tareapp.dialog;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.presenter.NewTaskPresenter;
import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    //region INJECTION
    private NewTaskPresenter presenter;
    //endregion INJECTION

    //region VARIABLES
    private List<String> appTasks;
    //endregion VARIABLES

    //region CONSTRUCTOR
    public TaskAdapter(List<String> appTasks, NewTaskPresenter presenter){
        this.appTasks = appTasks;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        Log.d("'/'/ TASK ADAPTER", "onBindViewHolder: " + position);
        String appTask = appTasks.get(position);
        holder.bind(appTask);
    }

    @Override
    public int getItemCount() {
        return appTasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaskName;
        ImageView ivNew;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTaskName = itemView.findViewById(R.id.tvTaskNameItemTask);
            ivNew = itemView.findViewById(R.id.ivNewTaskItemTask);
        }

        public void bind(String taskName){
            tvTaskName.setText(taskName);
        }
    }
}
