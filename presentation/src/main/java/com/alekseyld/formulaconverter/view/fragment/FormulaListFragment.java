package com.alekseyld.formulaconverter.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.alekseyld.formulaconverter.R;
import com.alekseyld.formulaconverter.adapter.FormulaAdapter;
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

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private FormulaAdapter mFormulaAdapter;

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
        setHasOptionsMenu(true);

        mFormulaAdapter = new FormulaAdapter();
        formulaList.setLayoutManager(new LinearLayoutManager(getContext()));
        formulaList.setAdapter(mFormulaAdapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mFormulaAdapter.setPresenter(mPresenter);
        mFormulaAdapter.setFragment(this);
        mPresenter.getFormulas();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_formula_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_get_formula:
                mPresenter.shareFormula(null, this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public FormulaAdapter getAdapter() {
        return mFormulaAdapter;
    }

    @OnClick(R.id.fab)
    public void onFabClick(){
        mPresenter.openFormulaFragment(false, null);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        formulaList.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        formulaList.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initializeInjections() {
        getComponent(MainComponent.class).inject(this);
    }
}
