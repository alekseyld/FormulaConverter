package com.alekseyld.formulaconverter.service;

import com.alekseyld.formulaconverter.entity.Formula;

import rx.Observable;

/**
 * Created by Alekseyld on 04.11.2016.
 */

public interface EntityService {

    Observable<Boolean> saveEntity(Formula formula);
    Observable<Formula> getEntity();

}
