package com.alekseyld.formulaconverter.usecase;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.executor.PostExecutionThread;
import com.alekseyld.formulaconverter.executor.ThreadExecutor;
import com.alekseyld.formulaconverter.service.FormulaService;
import com.alekseyld.formulaconverter.usecase.base.UseCase;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Alekseyld on 04.11.2016.
 */

public class GetFormulaUseCase extends UseCase<FormulaService> {

    private int id;

    @Inject
    public GetFormulaUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                             FormulaService formulaService) {
        super(threadExecutor, postExecutionThread, formulaService);
    }

    @Override
    protected Observable<Formula> buildUseCaseObservable() {
        return mService.getFormula(id);
    }

    public GetFormulaUseCase setId(int id) {
        this.id = id;
        return this;
    }
}
