package com.alekseyld.formulaconverter;

import android.app.Application;

import com.alekseyld.formulaconverter.internal.di.component.ApplicationComponent;
import com.alekseyld.formulaconverter.internal.di.component.DaggerApplicationComponent;
import com.alekseyld.formulaconverter.internal.di.module.ApplicationModule;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public class AndroidApplication extends Application {

    public final static String UUID = "b006b88e-a5d2-11e7-abc4-cec278b6b50a";

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeDBFlow();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initializeDBFlow(){
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
