package com.chrisgalhur.tareapp.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    public MyPreferences(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(IS_FIRST_TIME_LAUNCH, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    //region FIRST_TIME_LAUNCH
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
    //endregion FIRST_TIME_LAUNCH
}
