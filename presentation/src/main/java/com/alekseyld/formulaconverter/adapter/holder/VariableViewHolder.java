package com.alekseyld.formulaconverter.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alekseyld.formulaconverter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alekseyld on 25.09.2017.
 */

public class VariableViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.var_name)
    public TextView name;

    @BindView(R.id.var_value)
    public EditText value;

    public VariableViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}

