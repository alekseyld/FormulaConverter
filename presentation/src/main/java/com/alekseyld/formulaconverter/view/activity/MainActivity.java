package com.alekseyld.formulaconverter.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.alekseyld.formulaconverter.R;
import com.alekseyld.formulaconverter.internal.di.component.DaggerMainComponent;
import com.alekseyld.formulaconverter.internal.di.component.MainComponent;
import com.alekseyld.formulaconverter.internal.di.module.MainModule;
import com.alekseyld.formulaconverter.view.activity.base.BaseInjectorActivity;
import com.alekseyld.formulaconverter.view.fragment.FormulaListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseInjectorActivity<MainComponent> {

    @BindView(R.id.nav_view)
    NavigationView navigation;

    @BindView(R.id.drawer)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addFragment(FormulaListFragment.newInstance());
    }

    @Override
    protected MainComponent initializeInjections() {
        return DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mainModule(new MainModule(this))
                .build();
    }

    @Override
    public void onBackPressed() {
        if(this.getSupportFragmentManager().getBackStackEntryCount() > 0){
            this.getSupportFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }
    }
}
