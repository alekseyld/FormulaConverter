package com.alekseyld.formulaconverter.entity;

import com.alekseyld.formulaconverter.listeners.FormulaChangeListener;
import com.alekseyld.formulaconverter.utils.ExpressionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Alekseyld on 21.09.2017.
 */

public class Formula implements Serializable{

    private int id;

    private String name;

    private String rawFormula;

    private FormulaChangeListener formulaChangeListener;

    private Map<String, BigDecimal> vars;

    public Formula(String rawFormula) {
        this.rawFormula = rawFormula.replace(" ", "");
    }

    public String getRawFormula() {
        return rawFormula;
    }

    public Formula setRawFormula(String rawFormula) {
        this.rawFormula = rawFormula.replace(" ", "");

        if (formulaChangeListener != null)
            formulaChangeListener.onFormulaChange(this);

        return this;
    }

    public String getViewFormula() {
        return "$$" + rawFormula + "$$";
    }

    public String getViewInLineFormula() {
        return "\\(" + rawFormula + "\\)";
    }

    public Map<String, BigDecimal> findVars(){
        this.vars = ExpressionUtils.getVars(ExpressionUtils.sortingStation(rawFormula));

        return vars;
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

    public String getStringResult() {
        StringBuilder stringBuilder = new StringBuilder(getViewFormula());

        for(String key: vars.keySet()){
            stringBuilder.append("\n")
                    .append("$$")
                    .append(key).append("= ").append(vars.get(key).toString())
                    .append("$$");
        }

        stringBuilder.append("\n")
                .append("$$")
                .append("Result = ").append(ExpressionUtils.calculateExpressionWithVar(rawFormula, vars).toString())
                .append("$$");

        return stringBuilder.toString();
    }

    public int getId() {
        return id;
    }

    public Formula setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Formula setName(String name) {
        this.name = name;
        return this;
    }
}
