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

public class SaveFormulaUseCase extends UseCase<FormulaService> {

    private Formula formula;

    @Inject
    public SaveFormulaUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                              FormulaService formulaService) {
        super(threadExecutor, postExecutionThread, formulaService);
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable() {
        return mService.saveFormula(formula);
    }

    public SaveFormulaUseCase setFormula(Formula formula) {
        this.formula = formula;
        return this;
    }
}
