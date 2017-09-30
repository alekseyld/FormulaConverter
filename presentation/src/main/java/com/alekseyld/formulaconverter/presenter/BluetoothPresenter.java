package com.alekseyld.formulaconverter.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.alekseyld.formulaconverter.bluetooth.ClientThread;
import com.alekseyld.formulaconverter.bluetooth.Communicator;
import com.alekseyld.formulaconverter.bluetooth.CommunicatorImpl;
import com.alekseyld.formulaconverter.bluetooth.CommunicatorService;
import com.alekseyld.formulaconverter.bluetooth.ServerThread;
import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.presenter.base.BasePresenter;
import com.alekseyld.formulaconverter.rx.subscriber.BaseSubscriber;
import com.alekseyld.formulaconverter.usecase.SaveFormulaUseCase;
import com.alekseyld.formulaconverter.view.BluetoothView;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by Alekseyld on 30.09.2017.
 */

public class BluetoothPresenter extends BasePresenter<BluetoothView> {

    private class WriteTask extends AsyncTask<String, Void, Void> {
        protected Void doInBackground(String... args) {
            try {
                mClientThread.getCommunicator().write(args[0]);
            } catch (Exception e) {
                Log.d("MainActivity", e.getClass().getSimpleName() + " " + e.getLocalizedMessage());
            }
            return null;
        }
    }

    private SaveFormulaUseCase mSaveFormulaUseCase;

    private final CommunicatorService mCommunicatorService;
    private BluetoothAdapter mBluetoothAdapter;
    private BroadcastReceiver mDiscoverDevicesReceiver;
    private BroadcastReceiver mDiscoveryFinishedReceiver;

    private ServerThread mServerThread;
    private ClientThread mClientThread;

    @Inject
    BluetoothPresenter(SaveFormulaUseCase saveFormulaUseCase) {
        mSaveFormulaUseCase = saveFormulaUseCase;
        mCommunicatorService = new CommunicatorService() {
            @Override
            public Communicator createCommunicatorThread(BluetoothSocket socket) {
                return new CommunicatorImpl(socket, new CommunicatorImpl.CommunicationListener() {
                    @Override
                    public void onMessage(final String message) {
                        mView.getBaseActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onFormulaReceived(message.split("\\}")[0]+"}");
                            }
                        });
                    }
                });
            }
        };
    }

    @Override
    public void resume() {
        super.resume();

        if (!mBluetoothAdapter.isEnabled())
            requestEnableBluetooth();

        mServerThread = new ServerThread(mCommunicatorService);
        mServerThread.start();
    }

    @Override
    public void pause() {
        super.pause();

        mBluetoothAdapter.cancelDiscovery();

        if (mDiscoverDevicesReceiver != null) {
            try {
                mView.getBaseActivity().unregisterReceiver(mDiscoverDevicesReceiver);
            } catch (Exception e) {
                Log.d("BluetoothPresenter", "Не удалось отключить ресивер " + mDiscoverDevicesReceiver);
            }
        }

        if (mClientThread != null) {
            mClientThread.cancel();
        }

        if (mServerThread != null) {
            mServerThread.cancel();
        }
    }

    public void onDeviceSelect(BluetoothDevice deviceSelected) {
        if (mClientThread != null) {
            mClientThread.cancel();
        }

        mClientThread = new ClientThread(deviceSelected, mCommunicatorService);
        mClientThread.start();

        mView.showError("Вы подключились к устройству \"" + deviceSelected.getName() + "\"");
    }

    public void findDevices() {
        //// TODO: 30.09.2017 проверять включен ли блютуз

        if (mDiscoverDevicesReceiver == null) {
            mDiscoverDevicesReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    Log.d("BluetoothPresenter", "Find device " + device.getName());

                    mView.getAdapter().add(device);
                    mView.getAdapter().notifyDataSetChanged();

                    mView.getBaseActivity().unregisterReceiver(mDiscoverDevicesReceiver);
                }
            };
        }

        if (mDiscoveryFinishedReceiver == null) {
            mDiscoveryFinishedReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    mView.hideLoading();

                    Toast.makeText(mView.getContext(), "Поиск закончен. Выберите устройство для отправки  cообщения.", Toast.LENGTH_LONG).show();

                    mView.getBaseActivity().unregisterReceiver(mDiscoveryFinishedReceiver);
                    mBluetoothAdapter.cancelDiscovery();
        }
    };
        }

        mView.getBaseActivity().registerReceiver(mDiscoverDevicesReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
        mView.getBaseActivity().registerReceiver(mDiscoveryFinishedReceiver, new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED));

        mView.showLoading();

        mBluetoothAdapter.startDiscovery();
    }

    public void sendFormula(Formula formula) {
        if (formula == null) {
            mView.showError("Формула null");
            return;
        }

        if (mClientThread != null) {
            Gson gson = new Gson();

            new WriteTask().execute(
                    gson.toJson(new Formula(formula.getRawFormula()).setName(formula.getName()))
            );
        } else {
            mView.showError("Сначала выберите клиента");
        }
    }

    private void requestEnableBluetooth(){
        Intent i = new Intent(
                BluetoothAdapter.ACTION_REQUEST_ENABLE);
        mView.getBaseActivity().startActivity(i);
    }

    public void makeVisible() {
        Intent i = new Intent(
                BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        i.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        mView.getBaseActivity().startActivity(i);
        mView.showLoading();
    }

    private void onFormulaReceived(String jsonFormula) {

        mSaveFormulaUseCase.setFormula(new Gson().fromJson(jsonFormula, Formula.class)).execute(new BaseSubscriber() {
            @Override
            public void onCompleted() {
                super.onCompleted();
                Log.d("BluetoothPresenter", "formula saved");
                mView.onFormulaReceivedAndSave();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError(e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void setBluetoothAdapter(BluetoothAdapter bluetoothAdapter) {
        this.mBluetoothAdapter = bluetoothAdapter;
    }
}
