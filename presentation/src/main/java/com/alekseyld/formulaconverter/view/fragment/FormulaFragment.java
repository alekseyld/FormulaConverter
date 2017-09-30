package com.alekseyld.formulaconverter.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.alekseyld.formulaconverter.R;
import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.internal.di.component.MainComponent;
import com.alekseyld.formulaconverter.listeners.FormulaChangeListener;
import com.alekseyld.formulaconverter.presenter.FormulaPresenter;
import com.alekseyld.formulaconverter.view.FormulaView;
import com.alekseyld.formulaconverter.view.fragment.base.BaseFragment;
import com.alekseyld.formulaconverter.view.fragment.dialog.VariableDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.kexanie.library.MathView;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public class FormulaFragment extends BaseFragment<FormulaPresenter> implements FormulaView {

    @BindView(R.id.formula_view)
    MathView mathView;

    @BindView(R.id.formula_name)
    EditText formulaNameEditText;

    @BindView(R.id.input_formula)
    EditText inputEditText;

    boolean isView;

    public static FormulaFragment newInstance(boolean isView, Formula formula){
        FormulaFragment formulaFragment = new FormulaFragment();
        Bundle bundle = new Bundle();

        bundle.putBoolean("isView", isView);
        bundle.putSerializable("formula", formula);

        formulaFragment.setArguments(bundle);
        return formulaFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);

        setHasOptionsMenu(true);

        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        isView = getArguments().getBoolean("isView");

        if (isView) {
            inputEditText.setEnabled(false);
            formulaNameEditText.setEnabled(false);
        }

        Formula formula = (Formula) getArguments().getSerializable("formula");

        if (formula != null){
            getActivity().setTitle(formula.getName());
        } else {
            getActivity().setTitle("Создание формулы");
        }

        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPresenter.getFormula().setRawFormula(s.toString());
            }
        });

        return v;
    }

    @Override
    public void setMathView(String s) {
        mathView.setText(s);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getArguments().getSerializable("formula") != null){
            mPresenter.setFormula((Formula) getArguments().getSerializable("formula"));

            formulaNameEditText.setText(mPresenter.getFormula().getName());
            inputEditText.setText(mPresenter.getFormula().getRawFormula());
            mathView.setText(mPresenter.getFormula().getViewFormula());

            getArguments().putSerializable("formula", null);
        }

        mPresenter.getFormula().setFormulaChangeListener(new FormulaChangeListener() {
            @Override
            public void onFormulaChange(Formula formula) {
                mathView.setText(formula.getViewFormula());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_formula, menu);

        if (isView)
            menu.findItem(R.id.action_save).setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_save:
                mPresenter.getFormula()
                        .setName(formulaNameEditText.getText().toString());
                mPresenter.saveFormula();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFormulaSave() {
        showError("Формула сохранена");
        getBaseActivity().onBackPressed();
    }

    @OnClick(R.id.button_calculate)
    void onButtonCalculateClick(){
        if (mPresenter.getFormula().getRawFormula().equals("")){
            Toast.makeText(getContext(), "Проверьте правильность заполнения", Toast.LENGTH_SHORT).show();
            return;
        }

        VariableDialogFragment groupInputDialogFragment = VariableDialogFragment.newInstance(mPresenter);
        groupInputDialogFragment.setTargetFragment(this, 2);
        groupInputDialogFragment.show(getFragmentManager(), VariableDialogFragment.class.getSimpleName());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    protected void initializeInjections() {
        getComponent(MainComponent.class).inject(this);
    }
}
