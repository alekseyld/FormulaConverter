package com.alekseyld.formulaconverter.usecase;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.executor.PostExecutionThread;
import com.alekseyld.formulaconverter.executor.ThreadExecutor;
import com.alekseyld.formulaconverter.service.FormulaService;
import com.alekseyld.formulaconverter.usecase.base.UseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Alekseyld on 04.11.2016.
 */

public class GetAllFormulasUseCase extends UseCase<FormulaService> {

    @Inject
    public GetAllFormulasUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                 FormulaService formulaService) {
        super(threadExecutor, postExecutionThread, formulaService);
    }

    @Override
    protected Observable<List<Formula>> buildUseCaseObservable() {
        return mService.getAllFormulas();
    }
}
