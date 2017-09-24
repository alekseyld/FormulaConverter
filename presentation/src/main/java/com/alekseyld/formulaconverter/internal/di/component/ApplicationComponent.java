package com.alekseyld.formulaconverter.internal.di.component;

import android.content.Context;

import com.alekseyld.formulaconverter.executor.PostExecutionThread;
import com.alekseyld.formulaconverter.executor.ThreadExecutor;
import com.alekseyld.formulaconverter.internal.di.module.ApplicationModule;
import com.alekseyld.formulaconverter.view.activity.base.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;
/**
 * Created by Alekseyld on 02.09.2016.
 */

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
}
