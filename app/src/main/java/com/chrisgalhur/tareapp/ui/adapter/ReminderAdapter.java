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

    private final List<Reminder> reminders;
    private final DateTimeFormatter dateFormat;

    public ReminderAdapter(List<Reminder> reminders) {
        this.reminders = reminders;
        this.dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm", Locale.getDefault());
    }

    @NonNull
    @Override
    public ReminderAdapter.ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.ReminderViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);
        holder.tvReminderName.setText(reminder.getName());
        //Solo la fecha para formattedDate y solo la hora para formattedTime
        String formattedDate = reminder.getReminderDate().format(dateFormat);
        holder.tvReminderDate.setText(formattedDate.split(" ")[0]);
        holder.tvReminderTime.setText(formattedDate.split(" ")[1]);
        holder.tvReminderDescription.setText(reminder.getDescription());
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

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
}
