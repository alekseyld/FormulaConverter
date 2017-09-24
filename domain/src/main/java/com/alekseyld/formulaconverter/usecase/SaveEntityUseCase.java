package com.alekseyld.formulaconverter.usecase;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.executor.PostExecutionThread;
import com.alekseyld.formulaconverter.executor.ThreadExecutor;
import com.alekseyld.formulaconverter.service.EntityService;
import com.alekseyld.formulaconverter.usecase.base.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Alekseyld on 04.11.2016.
 */

public class SaveEntityUseCase extends UseCase<EntityService> {

    private Formula formula;

    @Inject
    public SaveEntityUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             EntityService entityService) {
        super(threadExecutor, postExecutionThread, entityService);
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable() {
        return mService.saveEntity(formula);
    }

    public SaveEntityUseCase setFormula(Formula formula) {
        this.formula = formula;
        return this;
    }
}
