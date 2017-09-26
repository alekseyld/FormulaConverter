package com.alekseyld.formulaconverter.service;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.repository.base.FormulaRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Alekseyld on 04.11.2016.
 */

public class FormulaServiceImpl implements FormulaService {

    private FormulaRepository mFormulaRepository;

    @Inject
    public FormulaServiceImpl(FormulaRepository formulaRepository) {
        mFormulaRepository = formulaRepository;
    }

    @Override
    public Observable<Boolean> saveFormula(Formula formula) {
        return Observable.just(
                mFormulaRepository.saveFormula(formula)
        );
    }

    @Override
    public Observable<Formula> getFormula(int id) {
        return Observable.just(
                mFormulaRepository.getFormula(id)
        );
    }

    @Override
    public Observable<List<Formula>> getAllFormulas() {
        return Observable.just(
                mFormulaRepository.getAll()
        );
    }
}
