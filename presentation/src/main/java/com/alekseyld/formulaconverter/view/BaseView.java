package com.alekseyld.formulaconverter.view;

import android.content.Context;

import com.alekseyld.formulaconverter.view.activity.base.BaseActivity;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showError(String message);

    Context getContext();

    BaseActivity getBaseActivity();

}
