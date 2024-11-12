package com.chrisgalhur.tareapp.entity.intern;

import android.app.Application;
import android.content.Context;

import java.util.Locale;

public class MyApp extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        updateBaseContextLocale(this);
    }

    private Context updateBaseContextLocale(Context context) {
        UserPreferences userPreferences = new UserPreferences(context);
        String language = userPreferences.getLanguage();
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        android.content.res.Resources resources = context.getResources();
        android.content.res.Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);

        return context.createConfigurationContext(configuration);
    }
}
