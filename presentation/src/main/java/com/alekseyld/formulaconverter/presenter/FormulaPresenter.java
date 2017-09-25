package com.alekseyld.formulaconverter.presenter;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.presenter.base.BasePresenter;
import com.alekseyld.formulaconverter.view.MainView;

import java.math.BigDecimal;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public class FormulaPresenter extends BasePresenter<MainView> {

    private Formula formula = new Formula("");

    @Inject
    FormulaPresenter(){
    }

    public Map<String, BigDecimal> getVars(){
        return formula.findVars();
    }

    public void calculateExp(Map<String, BigDecimal> vars) {

        formula.setVars(vars);

        mView.showError(formula.getStringResult());
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }
}
