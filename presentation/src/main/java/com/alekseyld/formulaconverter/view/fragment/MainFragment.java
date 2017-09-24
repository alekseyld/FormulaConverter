package com.alekseyld.formulaconverter.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alekseyld.formulaconverter.R;
import com.alekseyld.formulaconverter.internal.di.component.MainComponent;
import com.alekseyld.formulaconverter.presenter.MainPresenter;
import com.alekseyld.formulaconverter.view.MainView;
import com.alekseyld.formulaconverter.view.fragment.base.BaseFragment;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public class MainFragment extends BaseFragment<MainPresenter> implements MainView {

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setTitle(R.string.app_name);

        return v;
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
            Log.e("MainFragment", "Activity is null \n"+message);
    }

    @Override
    protected void initializeInjections() {
        getComponent(MainComponent.class).inject(this);
    }
}
