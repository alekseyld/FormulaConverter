package com.alekseyld.formulaconverter.internal.di.module;

import android.app.Activity;

import com.alekseyld.formulaconverter.internal.di.PerActivity;
import com.alekseyld.formulaconverter.repository.FormulaRepositoryImpl;
import com.alekseyld.formulaconverter.repository.base.FormulaRepository;
import com.alekseyld.formulaconverter.service.FormulaService;
import com.alekseyld.formulaconverter.service.FormulaServiceImpl;

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
    FormulaService provideEntityService(FormulaServiceImpl entityService){
        return entityService;
    }

    @PerActivity @Provides
    FormulaRepository provideEntityRepository(FormulaRepositoryImpl entityRepository){
        return entityRepository;
    }
}
