package com.alekseyld.formulaconverter.internal.di.module;

import android.app.Activity;

import com.alekseyld.formulaconverter.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alekseyld on 02.09.2016.
 */

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}