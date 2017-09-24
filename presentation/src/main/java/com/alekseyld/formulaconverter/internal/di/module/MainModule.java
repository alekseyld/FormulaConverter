package com.alekseyld.formulaconverter.internal.di.module;

import android.app.Activity;

import com.alekseyld.formulaconverter.internal.di.PerActivity;
import com.alekseyld.formulaconverter.repository.EntityRepositoryImpl;
import com.alekseyld.formulaconverter.repository.base.EntityRepository;
import com.alekseyld.formulaconverter.service.EntityService;
import com.alekseyld.formulaconverter.service.EntityServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Alekseyld on 02.09.2016.
 */

@Module
public class MainModule {

    private final Activity activity;

    public MainModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }

    @PerActivity @Provides
    EntityService provideEntityService(EntityServiceImpl entityService){
        return entityService;
    }

    @PerActivity @Provides
    EntityRepository provideEntityRepository(EntityRepositoryImpl entityRepository){
        return entityRepository;
    }
}
