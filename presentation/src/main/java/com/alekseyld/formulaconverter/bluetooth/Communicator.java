package com.alekseyld.formulaconverter.bluetooth;

/**
 * Created by Alekseyld on 30.09.2017.
 */

public interface Communicator {
    void startCommunication();
    void write(String message);
    void stopCommunication();
}
