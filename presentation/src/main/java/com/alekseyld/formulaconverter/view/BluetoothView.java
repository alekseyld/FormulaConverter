package com.alekseyld.formulaconverter.view;

import android.bluetooth.BluetoothDevice;
import android.widget.ArrayAdapter;

/**
 * Created by Alekseyld on 30.09.2017.
 */

public interface BluetoothView extends BaseView {

    ArrayAdapter<BluetoothDevice> getAdapter();

    void onFormulaReceivedAndSave();
}
