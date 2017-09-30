package com.alekseyld.formulaconverter.bluetooth;

import android.bluetooth.BluetoothSocket;

/**
 * Created by Alekseyld on 30.09.2017.
 */

public interface CommunicatorService {
    Communicator createCommunicatorThread(BluetoothSocket socket);
}
