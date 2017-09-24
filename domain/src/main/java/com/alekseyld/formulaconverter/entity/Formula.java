package com.alekseyld.formulaconverter.entity;

import com.alekseyld.formulaconverter.listeners.FormulaChangeListener;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Alekseyld on 21.09.2017.
 */

public class Formula {

    private String rawFormula;

    private String viewFormula;

    private String polishFormula;

    private FormulaChangeListener formulaChangeListener;

    private Map<String, BigDecimal> vars;

    public Formula(String rawFormula) {
        this.rawFormula = rawFormula;
    }

    public String getRawFormula() {
        return rawFormula;
    }

    public Formula setRawFormula(String rawFormula) {
        this.rawFormula = rawFormula;
        //// TODO: 24.09.2017
        this.viewFormula = "$$" + rawFormula + "$$";

        if (formulaChangeListener != null)
            formulaChangeListener.onFormulaChange(this);

        return this;
    }

    public String getViewFormula() {
        return viewFormula;
    }

    public Formula setViewFormula(String viewFormula) {
        this.viewFormula = viewFormula;
        return this;
    }

    public Map<String, BigDecimal> getVars() {
        return vars;
    }

    public Formula setVars(Map<String, BigDecimal> vars) {
        this.vars = vars;
        return this;
    }

    public Formula setFormulaChangeListener(FormulaChangeListener formulaChangeListener) {
        this.formulaChangeListener = formulaChangeListener;
        return this;
    }
}
