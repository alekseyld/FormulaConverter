package com.alekseyld.formulaconverter.view.fragment.dialog;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alekseyld.formulaconverter.R;
import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.internal.di.component.MainComponent;
import com.alekseyld.formulaconverter.presenter.BluetoothPresenter;
import com.alekseyld.formulaconverter.view.BluetoothView;
import com.alekseyld.formulaconverter.view.fragment.base.BaseDialogFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Alekseyld on 30.09.2017.
 */

public class BluetoothDialogFragment extends BaseDialogFragment<BluetoothPresenter> implements BluetoothView {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.devices_list)
    ListView devicesList;

    @BindView(R.id.send_ll)
    LinearLayout sendLL;

    @BindView(R.id.action_make_visible)
    Button buttonMakeVisible;

    ArrayAdapter<BluetoothDevice> listAdapter;
    List<BluetoothDevice> discoveredDevices;

    private Formula formula;

    public BluetoothDialogFragment setFormula(Formula formula) {
        this.formula = formula;
        return this;
    }

    public static BluetoothDialogFragment newInstance(Formula formula) {
        return new BluetoothDialogFragment().setFormula(formula);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.dialog_bluetooth, null);
        ButterKnife.bind(this, v);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(v)
                .setCancelable(false);

        if (formula == null) {
            sendLL.setVisibility(View.GONE);

            return builder.setTitle("Получение формулы").create();
        }

        buttonMakeVisible.setVisibility(View.GONE);

        discoveredDevices = new ArrayList<>();

        listAdapter = new ArrayAdapter<BluetoothDevice>(getContext(), android.R.layout.simple_list_item_1, discoveredDevices) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                final BluetoothDevice device = getItem(position);
                ((TextView) view.findViewById(android.R.id.text1)).setText(device.getName());
                return view;
            }
        };
        devicesList.setAdapter(listAdapter);

        builder.setTitle("Отправка формулы")
                .setPositiveButton("Отправить формулу", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sendMessage();
                dialog.cancel();
            }}).setNegativeButton("Отмена",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        return builder.create();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.BLUETOOTH_ADMIN)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN}, 1);
            }
        }
    }

    @Override
    public void onResume() {
        mPresenter.setBluetoothAdapter(
                ((BluetoothManager) getContext().getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter()
        );

        if (formula != null) {
            discoveredDevices.clear();
            listAdapter.notifyDataSetChanged();

            devicesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mPresenter.onDeviceSelect(discoveredDevices.get(position));
                }
            });
        }

        super.onResume();
    }

    public void sendMessage() {
        mPresenter.sendFormula(formula);
    }

    @OnClick(R.id.action_make_visible)
    public void makeVisible() {
        mPresenter.makeVisible();
    }

    @OnClick(R.id.action_find_devices)
    public void findDevices() {
        discoveredDevices.clear();
        listAdapter.notifyDataSetChanged();

        mPresenter.findDevices();
    }

    @Override
    public void onFormulaReceivedAndSave() {
        getTargetFragment().onResume();
        showToastMessage("Формула успешно получена");
        getDialog().cancel();
    }

    @Override
    public ArrayAdapter<BluetoothDevice> getAdapter() {
        return listAdapter;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        devicesList.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        devicesList.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initializeInjections() {
        getComponent(MainComponent.class).inject(this);
    }
}
