package com.alekseyld.formulaconverter.entity;

import com.alekseyld.formulaconverter.listeners.FormulaChangeListener;
import com.alekseyld.formulaconverter.utils.ExpressionUtils;

import java.io.Serializable;
import java.util.Map;

import static com.alekseyld.formulaconverter.utils.ExpressionUtils.MATH_TRANSFORM;

/**
 * Created by Alekseyld on 21.09.2017.
 */

public class Formula implements Serializable {

    private int id;

    private String name;

    private String rawFormula;

    private FormulaChangeListener formulaChangeListener;

    private Map<String, Double> vars;

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

    public Map<String, Double> findVars() {
        this.vars = ExpressionUtils.getVars(ExpressionUtils.sortingStation(formatFormula(rawFormula)));

        return vars;
    }

    public Map<String, Double> getVars() {
        return vars;
    }

    public Formula setVars(Map<String, Double> vars) {
        this.vars = vars;
        return this;
    }

    public Formula setFormulaChangeListener(FormulaChangeListener formulaChangeListener) {
        this.formulaChangeListener = formulaChangeListener;
        return this;
    }

    public String getStringResult() {
        StringBuilder stringBuilder = new StringBuilder(getViewFormula());

        for (String key : vars.keySet()) {
            stringBuilder.append("\n")
                    .append("$$")
                    .append(key).append("= ").append(vars.get(key).toString())
                    .append("$$");
        }

        stringBuilder.append("\n")
                .append("$$")
                .append("Result = ").append(ExpressionUtils.calculateExpressionWithVar(formatFormula(rawFormula), vars).toString())
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

    public String formatFormula(String rawFormula) {
        if (rawFormula == null || rawFormula.equals(""))
            return "";

        StringBuilder formatedFomula = new StringBuilder(rawFormula + " ");

//        lg(x) -> (x) log 10
//        ln(x) -> (x) log 2.71
//        log_5(x) -> (x) log 5

        for (String func : MATH_TRANSFORM.keySet()) {
            while (formatedFomula.indexOf(func) != -1) {
                int index = formatedFomula.indexOf(func);
                int indexLast = index + func.length() - 1;
                int indexClose = formatedFomula.indexOf(")", indexLast);
                formatedFomula.delete(index, indexLast);
                formatedFomula.replace(indexClose - 1, indexClose - 1, MATH_TRANSFORM.get(func));
            }
        }

//        int index = formatedFomula.indexOf("lg(");
//        int indexLast = index + "lg(".length() - 1;
//        int indexClose = formatedFomula.indexOf(")", indexLast);
//        formatedFomula.delete(index, indexLast);
//        formatedFomula.replace(indexClose, indexClose, "log 2");
//
//        formatedFomula.toString()
//}
//
        return formatedFomula.toString();
    }
}
