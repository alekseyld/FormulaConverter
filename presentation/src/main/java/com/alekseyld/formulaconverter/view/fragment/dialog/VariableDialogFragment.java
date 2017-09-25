package com.alekseyld.formulaconverter.view.fragment.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alekseyld.formulaconverter.R;
import com.alekseyld.formulaconverter.adapter.VariableAdapter;
import com.alekseyld.formulaconverter.presenter.FormulaPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alekseyld on 07.09.2017.
 */

public class VariableDialogFragment extends DialogFragment {

    @BindView(R.id.var_list)
    RecyclerView varList;

    private FormulaPresenter mPresenter;

    private VariableAdapter mVariableAdapter;

    public static VariableDialogFragment newInstance(FormulaPresenter presenter) {
        VariableDialogFragment fragment = new VariableDialogFragment();
        fragment.setPresenter(presenter);
        return fragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_variable, null);
        ButterKnife.bind(this, v);

        varList.setLayoutManager(new LinearLayoutManager(getContext()));
        mVariableAdapter = new VariableAdapter();
        varList.setAdapter(mVariableAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton("Вычислить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //// TODO: 25.09.2017 проверять на заполенение

                        mPresenter.calculateExp(mVariableAdapter.getVars());
                        dialog.cancel();
                    }
                })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();

        mVariableAdapter.setVars(mPresenter.getVars());
    }

    public void setPresenter(FormulaPresenter presenter) {
        mPresenter = presenter;
    }
}
