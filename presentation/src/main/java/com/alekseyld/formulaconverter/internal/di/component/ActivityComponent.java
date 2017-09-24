package com.alekseyld.formulaconverter.internal.di.component;

import android.app.Activity;

import com.alekseyld.formulaconverter.internal.di.PerActivity;
import com.alekseyld.formulaconverter.internal.di.module.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  Activity activity();
}
