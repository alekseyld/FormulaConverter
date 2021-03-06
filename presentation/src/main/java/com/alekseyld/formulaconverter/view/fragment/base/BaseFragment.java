package com.alekseyld.formulaconverter.view.fragment.base;

/**
 * Created by Alekseyld on 02.09.2016.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.alekseyld.formulaconverter.internal.di.HasComponent;
import com.alekseyld.formulaconverter.presenter.base.BasePresenter;
import com.alekseyld.formulaconverter.view.BaseView;
import com.alekseyld.formulaconverter.view.activity.base.BaseActivity;

import javax.inject.Inject;

/**
 * Base {@link android.app.Fragment} class for every fragment in this application.
 */
public abstract class BaseFragment<TPresenter extends BasePresenter> extends Fragment implements BaseView{

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
            Log.e("BaseFragment", "onDestroy");
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

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

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
