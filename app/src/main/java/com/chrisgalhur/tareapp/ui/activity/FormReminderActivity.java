package com.chrisgalhur.tareapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.chrisgalhur.tareapp.R;
import com.chrisgalhur.tareapp.entity.Reminder;
import com.chrisgalhur.tareapp.model.FormReminderModel;
import com.chrisgalhur.tareapp.model.FormReminderModelImpl;
import com.chrisgalhur.tareapp.presenter.FormReminderPresenter;
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
    private TextInputEditText textInReminderName;
    private TextInputEditText textInReminderDescription;
    private Button btSelectDate;
    private Button btSelectTime;
    private TextView btnCancel;
    private TextView btnAccept;
    //endregion UI

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
        textInReminderName = findViewById(R.id.textInReminderNameFormReminder);
        textInReminderDescription = findViewById(R.id.textInReminderDescriptionFormReminder);
        btSelectDate = findViewById(R.id.btSelectDateFormReminder);
        btSelectTime = findViewById(R.id.btSelectTimeFormReminder);
        btnCancel = findViewById(R.id.btnCancelFormReminder);
        btnAccept = findViewById(R.id.btnAcceptFormReminder);

        btSelectDate.setOnClickListener(v -> presenter.onBtSelectDateClicked());

        btSelectTime.setOnClickListener(v -> presenter.onBtSelectTimeClicked());

        btnCancel.setOnClickListener(v -> finish());

        btnAccept.setOnClickListener(v -> presenter.onBtnAcceptClicked());
    }
    //endregion ON_CREATE

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
                btSelectTime.setText(hour + ":" + minute);
            }
        });
    }
    //endregion OPEN_TIME_PICKER

    //region SAVE_REMINDER
    @Override
    public void sendReminder() {
        String name = textInReminderName.getText().toString().trim();
        String description = textInReminderDescription.getText().toString().trim();

        if(name.isEmpty()) {
            textInReminderName.setError("Name is required");
            textInReminderName.setHintTextColor(getResources().getColor(R.color.red));
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

        LocalDateTime localDateTimeReminder = LocalDateTime.of(formYear, formMonth, formDay, formHour, formMinute);
        Reminder reminder = new Reminder(name, description, false, localDateTimeReminder);
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

        Intent intent = new Intent(FormReminderActivity.this, MainActivity.class);
        startActivity(intent);
    }
    //endregion SAVE_REMINDER
}