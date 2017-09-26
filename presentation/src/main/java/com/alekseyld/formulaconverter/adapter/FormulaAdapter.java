package com.alekseyld.formulaconverter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekseyld.formulaconverter.R;
import com.alekseyld.formulaconverter.adapter.holder.FormulaViewHolder;
import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.presenter.FormulaListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alekseyld on 26.09.2017.
 */

public class FormulaAdapter extends RecyclerView.Adapter<FormulaViewHolder> {

    private FormulaListPresenter mPresenter;

    private List<Formula> formulas = new ArrayList<>();

    public void setPresenter(FormulaListPresenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public FormulaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_formula, parent, false);

        return new FormulaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FormulaViewHolder holder, int position) {
        final Formula formula = formulas.get(position);

        holder.swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openFormulaFragment(true, formula);
            }
        });

        holder.name.setText(formula.getName());
        holder.mathView.setText(formula.getViewInLineFormula());
    }

    @Override
    public int getItemCount() {
        return formulas.size();
    }

    public void add(Formula formula){
        formulas.add(formula);
    }

    public void addAll(List<Formula> formulas){
        this.formulas.addAll(formulas);
    }

    public void clear(){
        formulas.clear();
    }
}
