package com.chrisgalhur.tareapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.entity.Reminder;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    //region PROPERTIES
    private final List<Reminder> reminders;
    private final DateTimeFormatter dateFormat;
    private final OnReminderClickListener listener;
    //endregion PROPERTIES

    //region CONSTRUCTOR
    public ReminderAdapter(List<Reminder> reminders, OnReminderClickListener listener) {
        this.reminders = reminders;
        this.dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.getDefault());
        this.listener = listener;
    }
    //endregion CONSTRUCTOR

    //region ON_CREATE_VIEW_HOLDER
    @NonNull
    @Override
    public ReminderAdapter.ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false);
        return new ReminderViewHolder(view);
    }
    //endregion ON_CREATE_VIEW_HOLDER

    //region ON_BIND_VIEW_HOLDER
    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ReminderViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);
        holder.tvReminderName.setText(reminder.getName());
        String formattedDate = reminder.getReminderDate().format(dateFormat);
        holder.tvReminderDate.setText(formattedDate.split(" ")[0]);
        holder.tvReminderTime.setText(formattedDate.split(" ")[1]);
        holder.tvReminderDescription.setText(reminder.getDescription());
        holder.itemView.setOnClickListener(v -> listener.onReminderClick(position));
    }
    //endregion ON_BIND_VIEW_HOLDER

    //region GET_ITEM_COUNT
    @Override
    public int getItemCount() {
        return reminders.size();
    }
    //endregion GET_ITEM_COUNT

    //region REMINDER_VIEW_HOLDER
    public static class ReminderViewHolder extends RecyclerView.ViewHolder {
        TextView tvReminderName;
        TextView tvReminderTime;
        TextView tvReminderDate;
        TextView tvReminderDescription;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReminderName = itemView.findViewById(R.id.tvReminderName);
            tvReminderTime = itemView.findViewById(R.id.tvReminderTime);
            tvReminderDate = itemView.findViewById(R.id.tvReminderDate);
            tvReminderDescription = itemView.findViewById(R.id.tvReminderDescription);
        }
    }
    //endregion REMINDER_VIEW_HOLDER

    //region ON_REMINDER_CLICK_LISTENER
    public interface OnReminderClickListener {
        void onReminderClick(int position);
    }
    //endregion ON_REMINDER_CLICK_LISTENER
}
