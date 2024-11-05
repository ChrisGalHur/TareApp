package com.chrisgalhur.tareapp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.entity.Reminder;
import com.chrisgalhur.tareapp.model.interf.FormReminderModel;
import com.chrisgalhur.tareapp.model.FormReminderModelImpl;
import com.chrisgalhur.tareapp.presenter.interf.FormReminderPresenter;
import com.chrisgalhur.tareapp.presenter.FormReminderPresenterImpl;
import com.chrisgalhur.tareapp.util.BaseActivity;
import com.chrisgalhur.tareapp.util.DatePickerUtil;
import com.chrisgalhur.tareapp.util.TimePickerUtil;
import com.chrisgalhur.tareapp.view.FormReminderView;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDateTime;

public class FormReminderActivity extends BaseActivity implements FormReminderView {

    //region INJECTION
    private FormReminderModel model;
    private FormReminderPresenter presenter;
    //endregion INJECTION

    //region VARIABLES
    private String reminderName;
    private String reminderDescription;
    private int formYear;
    private int formMonth;
    private int formDay;
    private int formHour;
    private int formMinute;
    //endregion VARIABLES

    //region UI
    private TextView tvTitle;
    private CardView btDeleteReminder;
    private TextInputEditText etReminderName;
    private TextInputEditText etReminderDescription;
    private Button btSelectDate;
    private Button btSelectTime;
    private TextView btnCancel;
    private TextView btnAccept;
    //endregion UI

    //region GET_CONTEXT
    @Override
    public Context getContext() {
        return this;
    }
    //endregion GET_CONTEXT

    //region ON_CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_reminder);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        model = new FormReminderModelImpl(this);
        presenter = new FormReminderPresenterImpl(this, model);

        tvTitle = findViewById(R.id.tvTitleFormReminder);
        btDeleteReminder = findViewById(R.id.btDeleteReminderFormReminder);
        etReminderName = findViewById(R.id.textInReminderNameFormReminder);
        etReminderDescription = findViewById(R.id.textInReminderDescriptionFormReminder);
        btSelectDate = findViewById(R.id.btSelectDateFormReminder);
        btSelectTime = findViewById(R.id.btSelectTimeFormReminder);
        btnCancel = findViewById(R.id.btnCancelFormReminder);
        btnAccept = findViewById(R.id.btnAcceptFormReminder);


        int reminderId = getIntent().getIntExtra(getString(R.string.extra_reminder_id), -1);

        if(reminderId != -1) { // Adecuamos la vista para editar un recordatorio
            tvTitle.setText(R.string.title_edit_reminder);
            btDeleteReminder.setVisibility(android.view.View.VISIBLE);
            btDeleteReminder.setOnClickListener(v -> presenter.onBtDeleteReminderClicked(reminderId));
            presenter.loadReminder(reminderId);
        }else { // Adecuamos la vista para crear un nuevo recordatorio
            tvTitle.setText(R.string.title_new_reminder);
            btDeleteReminder.setVisibility(android.view.View.GONE);
        }


        btSelectDate.setOnClickListener(v -> presenter.onBtSelectDateClicked());

        btSelectTime.setOnClickListener(v -> presenter.onBtSelectTimeClicked());

        btnCancel.setOnClickListener(v -> finish());

        btnAccept.setOnClickListener(v -> presenter.onBtnAcceptClicked());
    }
    //endregion ON_CREATE

    //region ON_DESTROY
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.clearDisposables();
    }
    //endregion ON_DESTROY

    //region OPEN_DATE_PICKER
    @Override
    public void openDatePicker() {
        DatePickerUtil.openDatePicker(this, new DatePickerUtil.DatePickerListener() {
            @Override
            public void onDateSelected(int year, int month, int day) {
                formYear = year;
                formMonth = month;
                formDay = day;
                btSelectDate.setText(day + "/" + month + "/" + year);
            }
        });
    }
    //endregion OPEN_DATE_PICKER

    //region OPEN_TIME_PICKER
    @Override
    public void openTimePicker() {
        TimePickerUtil.openTimePicker(this, new TimePickerUtil.TimePickerListener() {
            @Override
            public void onTimeSelected(int hour, int minute) {
                formHour = hour;
                formMinute = minute;
                String formattedTime = String.format("%02d:%02d", hour, minute);
                btSelectTime.setText(formattedTime);
            }
        });
    }
    //endregion OPEN_TIME_PICKER

    //region SEND_REMINDER_SAVE
    @Override
    public void sendReminderSave() {
        String name = etReminderName.getText().toString().trim();
        String description = etReminderDescription.getText().toString().trim();

        if(name.isEmpty()) {
            etReminderName.setError("Name is required");
            etReminderName.setHintTextColor(getResources().getColor(R.color.red));
            return;
        }

        if(!btSelectDate.getText().toString().contains("/")) {
            LocalDateTime now = LocalDateTime.now();
            formYear = now.getYear();
            formMonth = now.getMonthValue();
            formDay = now.getDayOfMonth();
        }

        if (!btSelectTime.getText().toString().contains(":")) {
            //mensaje de error desde strings
            Toast.makeText(this, R.string.error_time, Toast.LENGTH_SHORT).show();
            return;
        }

        int reminderId = getIntent().getIntExtra(getString(R.string.extra_reminder_id), -1);

        if(reminderId != -1) {
            presenter.updateReminder(
                    reminderId,
                    name,
                    description,
                    false,
                    formYear,
                    formMonth,
                    formDay,
                    formHour,
                    formMinute
            );
        }else {
            presenter.saveReminder(
                    name,
                    description,
                    false,
                    formYear,
                    formMonth,
                    formDay,
                    formHour,
                    formMinute
            );
        }
    }
    //endregion SEND_REMINDER_SAVE

    //region LOAD_REMINDER
    @Override
    public void loadReminder(Reminder reminder) {
        etReminderName.setText(reminder.getName());
        etReminderDescription.setText(reminder.getDescription());
        LocalDateTime reminderDate = reminder.getReminderDate();
        formYear = reminderDate.getYear();
        formMonth = reminderDate.getMonthValue();
        formDay = reminderDate.getDayOfMonth();
        formHour = reminderDate.getHour();
        formMinute = reminderDate.getMinute();
        btSelectDate.setText(String.format("%02d/%02d/%02d", formDay, formMonth, formYear));
        btSelectTime.setText(String.format("%02d:%02d", formHour, formMinute));
    }
    //endregion LOAD_REMINDER

    //region SHOW_ERROR
    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    //endregion SHOW_ERROR

    //region SHOW_SUCCESS
    @Override
    public void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        finish();
    }
    //endregion SHOW_SUCCESS
}