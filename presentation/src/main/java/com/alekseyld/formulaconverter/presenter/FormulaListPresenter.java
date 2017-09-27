package com.alekseyld.formulaconverter.presenter;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.presenter.base.BasePresenter;
import com.alekseyld.formulaconverter.rx.subscriber.BaseSubscriber;
import com.alekseyld.formulaconverter.usecase.DeleteFormulaUseCase;
import com.alekseyld.formulaconverter.usecase.GetAllFormulasUseCase;
import com.alekseyld.formulaconverter.view.FormulaListView;
import com.alekseyld.formulaconverter.view.fragment.FormulaFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Alekseyld on 25.09.2017.
 */

public class FormulaListPresenter extends BasePresenter<FormulaListView> {

    GetAllFormulasUseCase mGetAllFormulasUseCase;
    DeleteFormulaUseCase mDeleteFormulaUseCase;

    @Inject
    FormulaListPresenter(GetAllFormulasUseCase getAllFormulasUseCase,
                         DeleteFormulaUseCase deleteFormulaUseCase){
        mGetAllFormulasUseCase = getAllFormulasUseCase;
        mDeleteFormulaUseCase = deleteFormulaUseCase;
    }

    public void getFormulas() {
        mView.showLoading();
        mGetAllFormulasUseCase.execute(new BaseSubscriber<List<Formula>>(){
            @Override
            public void onNext(List<Formula> formulas) {
                super.onNext(formulas);
                mView.getAdapter().clear();
                mView.getAdapter().addAll(formulas);
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.hideLoading();
            }
        });
    }

    public void openFormulaFragment(boolean isView, Formula formula){
        mView.getBaseActivity().replaceFragment(FormulaFragment.newInstance(isView, formula));
    }

    public void deleteFormula(final Formula formula) {
        mDeleteFormulaUseCase.setFormula(formula).execute(new BaseSubscriber(){
            @Override
            public void onCompleted() {
                super.onCompleted();
                mView.getAdapter().remove(formula);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError(e.getMessage());
            }
        });
    }

    public void shareFormula(Formula formula) {
        //// TODO: 27.09.2017 реализовать шаринг
        mView.showError("Поделиться не реализовано");
    }
}
