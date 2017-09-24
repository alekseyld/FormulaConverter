package com.alekseyld.formulaconverter.repository.base;

import com.alekseyld.formulaconverter.entity.Formula;

/**
 * Created by Alekseyld on 04.09.2016.
 */

public interface EntityRepository {

    boolean saveEntity(Formula formula);
    Formula getEntity();

}
