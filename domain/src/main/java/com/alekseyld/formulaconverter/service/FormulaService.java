package com.alekseyld.formulaconverter.service;

import com.alekseyld.formulaconverter.entity.Formula;

import java.util.List;

import rx.Observable;

/**
 * Created by Alekseyld on 04.11.2016.
 */

public interface FormulaService {

    Observable<Boolean> saveFormula(Formula formula);
    Observable<Boolean> deleteFormula(Formula formula);

    Observable<Formula> getFormula(int id);
    Observable<List<Formula>> getAllFormulas();
}
