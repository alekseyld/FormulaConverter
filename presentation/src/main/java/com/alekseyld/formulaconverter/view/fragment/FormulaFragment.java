package com.alekseyld.formulaconverter.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.alekseyld.formulaconverter.view.MainView;
import com.alekseyld.formulaconverter.view.fragment.base.BaseFragment;
import com.alekseyld.formulaconverter.view.fragment.dialog.VariableDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.kexanie.library.MathView;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public class FormulaFragment extends BaseFragment<FormulaPresenter> implements MainView {

    @BindView(R.id.formula_view)
    MathView mathView;

    @BindView(R.id.input_formula)
    EditText inputEditText;

    public static FormulaFragment newInstance(){
        return new FormulaFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);

        getActivity().setTitle(R.string.app_name);
        setHasOptionsMenu(true);

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
    public void onResume() {
        super.onResume();

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
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_result:
                VariableDialogFragment groupInputDialogFragment = VariableDialogFragment.newInstance(mPresenter);
                groupInputDialogFragment.setTargetFragment(this, 2);
                groupInputDialogFragment.show(getFragmentManager(), VariableDialogFragment.class.getSimpleName());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
    }

    @Override
    public void showError(String message) {
        if(getActivity() != null)
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        else
            Log.e("FormulaFragment", "Activity is null \n"+message);
    }

    @Override
    protected void initializeInjections() {
        getComponent(MainComponent.class).inject(this);
    }
}
