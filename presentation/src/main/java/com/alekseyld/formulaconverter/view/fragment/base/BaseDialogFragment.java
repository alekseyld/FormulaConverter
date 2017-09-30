package com.alekseyld.formulaconverter.view.fragment.base;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alekseyld.formulaconverter.internal.di.HasComponent;
import com.alekseyld.formulaconverter.presenter.base.BasePresenter;
import com.alekseyld.formulaconverter.view.BaseView;
import com.alekseyld.formulaconverter.view.activity.base.BaseActivity;

import javax.inject.Inject;

/**
 * Created by Alekseyld on 30.09.2017.
 */

public abstract class BaseDialogFragment<TPresenter extends BasePresenter> extends DialogFragment implements BaseView {

    @Inject
    protected TPresenter mPresenter;

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter != null) {
            mPresenter.destroy();
        }else {
            Log.e("BaseDialogFragment", "onDestroy");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.pause();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initialize();
    }

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    @SuppressWarnings("unchecked")
    protected void initialize(){
        initializeInjections();
        mPresenter.setView(this);
    }

    @Override
    public BaseActivity getBaseActivity(){
        return (BaseActivity)this.getActivity();
    }

    @Override
    public void showError(String message) {
        if(getActivity() != null)
            Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
        else
            Log.e("FormulaFragment", "Activity is null \n" + message);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    protected abstract void initializeInjections();

}
