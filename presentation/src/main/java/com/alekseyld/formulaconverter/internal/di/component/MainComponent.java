package com.alekseyld.formulaconverter.internal.di.component;

import com.alekseyld.formulaconverter.internal.di.PerActivity;
import com.alekseyld.formulaconverter.internal.di.module.MainModule;
import com.alekseyld.formulaconverter.view.fragment.MainFragment;

import dagger.Component;

/**
 * Created by Alekseyld on 02.09.2016.
 */

@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {MainModule.class})
public interface MainComponent {
    void inject(MainFragment mainFragment);
}
