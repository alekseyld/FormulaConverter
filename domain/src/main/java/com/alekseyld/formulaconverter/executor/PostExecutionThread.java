package com.alekseyld.formulaconverter.executor;

import rx.Scheduler;

/**
 * Created by Alekseyld on 02.09.2016.
 */

public interface PostExecutionThread {
    Scheduler getScheduler();
}

