package com.alekseyld.formulaconverter.rx.subscriber;

import android.support.annotation.CallSuper;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public class BaseSubscriber<T> extends rx.Subscriber<T> {
    @Override @CallSuper  public void onCompleted() {
    }

    @Override @CallSuper public void onError(Throwable e) {
    }

    @Override @CallSuper public void onNext(T t) {
    }
}