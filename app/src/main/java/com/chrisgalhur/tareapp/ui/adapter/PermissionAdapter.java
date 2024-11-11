package com.chrisgalhur.tareapp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.entity.Permission;
import com.chrisgalhur.tareapp.util.PermissionHelper;

import java.util.List;

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.PermissionViewHolder> {

    private List<Permission> permissions;
    private Context context;
    private PermissionHelper permissionHelper;

    public PermissionAdapter(List<Permission> permissions, Context context) {
        this.permissions = permissions;
        this.context = context;
        permissionHelper = new PermissionHelper(context);

        for (Permission permission : permissions) {
            permission.setGranted(permissionHelper.isPermissionGranted(permission.getRealName()));
        }
    }

    @NonNull
    @Override
    public PermissionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permission, parent, false);
        return new PermissionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PermissionViewHolder holder, int position) {
        Permission permission = permissions.get(position);
        holder.tvPermissionName.setText(permission.getName());

        updatePermissionStatus(holder, permission);

        holder.btnRequestPermission.setOnClickListener(v -> requestPermission(holder, permission));
    }

    @Override
    public int getItemCount() {
        return permissions.size();
    }

    private void requestPermission(PermissionViewHolder holder, Permission permission) {
        switch (permission.getRealName()) {
            case "android.permission.POST_NOTIFICATIONS":
                permissionHelper.requestNotificationPermission((Activity) context);
                break;
            case "android.permission.SCHEDULE_EXACT_ALARM":
                permissionHelper.requestScheduleExactAlarmPermission((Activity) context);
                break;
            case "android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS":
                permissionHelper.requestIgnoreBatteryOptimizationsPermission((Activity) context);
                break;
            default:
                break;
        }

        // Update permission status after 1 second
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override public void run() {
                updatePermissionStatus(holder, permission);
            }
        }, 1000); // 1 second
    }

    private void updatePermissionStatus(PermissionViewHolder holder, Permission permission) {
        if (permissionHelper.isPermissionGranted(permission.getRealName())) {
            holder.tvPermissionName.setTextColor(ContextCompat.getColor(context, R.color.green_ok));
            holder.tvPermissionAccepted.setVisibility(View.VISIBLE);
            holder.tvPermissionDescription.setVisibility(View.GONE);
        } else {
            holder.tvPermissionName.setTextColor(ContextCompat.getColor(context, R.color.red_no_ok));
            holder.tvPermissionAccepted.setVisibility(View.GONE);
            holder.tvPermissionDescription.setText(permission.getDescription());
            holder.btnRequestPermission.setVisibility(View.VISIBLE);
            holder.btnRequestPermission.setVisibility(View.VISIBLE);
        }
    }

    public static class PermissionViewHolder extends RecyclerView.ViewHolder {
        TextView tvPermissionName;
        TextView tvPermissionDescription;
        TextView tvPermissionAccepted;
        Button btnRequestPermission;

        public PermissionViewHolder(View itemView) {
            super(itemView);
            tvPermissionName = itemView.findViewById(R.id.tvPermissionNamePermissionItem);
            tvPermissionDescription = itemView.findViewById(R.id.tvPermissionDescriptionPermissionItem);
            tvPermissionAccepted = itemView.findViewById(R.id.tvPermissionAcceptedPermissionItem);
            btnRequestPermission = itemView.findViewById(R.id.btnRequestPermissionPermissionItem);
        }
    }
}
