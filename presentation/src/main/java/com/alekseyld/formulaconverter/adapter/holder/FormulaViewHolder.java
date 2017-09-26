package com.alekseyld.formulaconverter.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alekseyld.formulaconverter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.kexanie.library.MathView;

/**
 * Created by Alekseyld on 26.09.2017.
 */

public class FormulaViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.formula_name)
    public TextView name;

    @BindView(R.id.formula)
    public MathView mathView;

    public FormulaViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}
