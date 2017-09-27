package com.alekseyld.formulaconverter.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alekseyld.formulaconverter.R;
import com.alekseyld.formulaconverter.adapter.holder.VariableViewHolder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.alekseyld.formulaconverter.utils.ExpressionUtils.DECIMAL_PATTERN;

/**
 * Created by Alekseyld on 25.09.2017.
 */

public class VariableAdapter extends RecyclerView.Adapter<VariableViewHolder> {

    private List<String> keys;
    private Map<String, BigDecimal> vars;

    @Override
    public VariableViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_variable, parent, false);

        return new VariableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VariableViewHolder holder, int position) {

        final String key = keys.get(position);

        holder.name.setText(key + " =");

        holder.value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(DECIMAL_PATTERN.matcher(s.toString()).find()){
                    vars.put(key, new BigDecimal(s.toString()));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return vars.size();
    }

    public void setVars(Map<String, BigDecimal> vars) {
        this.vars = vars;
        this.keys = new ArrayList<>(vars.keySet());
    }

    public Map<String, BigDecimal> getVars() {
        return vars;
    }
}
