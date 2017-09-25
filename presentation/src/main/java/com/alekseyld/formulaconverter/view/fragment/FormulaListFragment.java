package com.alekseyld.formulaconverter.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekseyld.formulaconverter.R;
import com.alekseyld.formulaconverter.internal.di.component.MainComponent;
import com.alekseyld.formulaconverter.presenter.FormulaListPresenter;
import com.alekseyld.formulaconverter.view.FormulaListView;
import com.alekseyld.formulaconverter.view.fragment.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Alekseyld on 25.09.2017.
 */

public class FormulaListFragment extends BaseFragment<FormulaListPresenter> implements FormulaListView {

    @BindView(R.id.formula_list)
    RecyclerView formulaList;

    public static FormulaListFragment newInstance(){
        return new FormulaListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_formula_list, container, false);
        ButterKnife.bind(this, v);

        getActivity().setTitle(R.string.app_name);
        getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        formulaList.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    @OnClick(R.id.fab)
    public void onFabClick(){
        getBaseActivity().replaceFragment(FormulaFragment.newInstance());
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