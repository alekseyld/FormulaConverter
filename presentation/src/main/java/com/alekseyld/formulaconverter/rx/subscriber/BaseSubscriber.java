package com.alekseyld.formulaconverter.rx.subscriber;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public class BaseSubscriber<T> extends rx.Subscriber<T> {
    @Override public void onCompleted() {
        // no-op by default.
    }

    @Override public void onError(Throwable e) {
    }

    @Override public void onNext(T t) {
        // no-op by default.
    }
}