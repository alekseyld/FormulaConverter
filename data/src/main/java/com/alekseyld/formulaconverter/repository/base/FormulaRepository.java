package com.alekseyld.formulaconverter.repository.base;

import com.alekseyld.formulaconverter.entity.Formula;

import java.util.List;

/**
 * Created by Alekseyld on 04.09.2016.
 */

public interface FormulaRepository {

    boolean saveFormula(Formula formula);
    boolean deleteFormula(Formula formula);

    Formula getFormula(int id);
    List<Formula> getAll();

}
