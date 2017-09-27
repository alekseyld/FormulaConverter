package com.alekseyld.formulaconverter.presenter;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.presenter.base.BasePresenter;
import com.alekseyld.formulaconverter.rx.subscriber.BaseSubscriber;
import com.alekseyld.formulaconverter.usecase.SaveFormulaUseCase;
import com.alekseyld.formulaconverter.view.FormulaView;

import java.math.BigDecimal;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public class FormulaPresenter extends BasePresenter<FormulaView> {

    private Formula mFormula = new Formula("");

    SaveFormulaUseCase mSaveFormulaUseCase;

    @Inject
    FormulaPresenter(SaveFormulaUseCase saveFormulaUseCase){
        mSaveFormulaUseCase = saveFormulaUseCase;
    }

    public Map<String, BigDecimal> getVars(){
        return mFormula.findVars();
    }

    public void calculateExp(Map<String, BigDecimal> vars) {

        for(String key: vars.keySet()){
            if (vars.get(key) == null){
                mView.showError("Проверьте правильность заполнения");
                return;
            }
        }

        mFormula.setVars(vars);
        mView.setMathView(mFormula.getStringResult());
    }

    public Formula getFormula() {
        return mFormula;
    }

    public void setFormula(Formula formula) {
        this.mFormula = formula;
    }

    public void saveFormula() {
        if(mFormula.getName() == null || mFormula.getRawFormula().equals("")|| mFormula.getName().equals("")){
            mView.showError("Проверьте правильность заполнения");
            return;
        }

        mSaveFormulaUseCase.setFormula(mFormula).execute(new BaseSubscriber<Boolean>(){
            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.onFormulaSave();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError(e.getMessage());
            }
        });
    }
}
