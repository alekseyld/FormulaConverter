package com.alekseyld.formulaconverter;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.alekseyld.formulaconverter.internal.di.component.ApplicationComponent;
import com.alekseyld.formulaconverter.internal.di.component.DaggerApplicationComponent;
import com.alekseyld.formulaconverter.internal.di.module.ApplicationModule;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
