package com.chrisgalhur.tareapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.entity.Reminder;

import java.time.LocalDateTime;
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

        LocalDateTime date = reminder.getReminderDate();
        String formattedDate = date.format(dateFormat);
        holder.tvReminderName.setText(reminder.getName());
        holder.tvReminderDate.setText(formattedDate.split(" ")[0]);
        holder.tvReminderTime.setText(formattedDate.split(" ")[1]);
        holder.tvReminderDescription.setText(reminder.getDescription());
        holder.itemView.setOnClickListener(v -> listener.onReminderClick(position));
        holder.btDeleteReminder.setOnClickListener(v -> onDeleteReminderClick(position));

        LocalDateTime now = LocalDateTime.now();

        if (date.isBefore(now)) {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.ultra_light_grey, null));
            holder.btDeleteReminder.setVisibility(View.VISIBLE);
        } else {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.white, null));
            holder.btDeleteReminder.setVisibility(View.GONE);
        }
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
        CardView btDeleteReminder;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReminderName = itemView.findViewById(R.id.tvReminderNameItemReminder);
            tvReminderTime = itemView.findViewById(R.id.tvReminderTimeItemReminder);
            tvReminderDate = itemView.findViewById(R.id.tvReminderDateItemReminder);
            tvReminderDescription = itemView.findViewById(R.id.tvReminderDescriptionItemReminder);
            btDeleteReminder = itemView.findViewById(R.id.btDeleteReminderItemReminder);
        }

    }
    //endregion REMINDER_VIEW_HOLDER

    //region EVENT LISTENERS
    public interface OnReminderClickListener {
        void onReminderClick(int position);
    }
    private void onDeleteReminderClick(int position) {

    }
    //endregion
}
