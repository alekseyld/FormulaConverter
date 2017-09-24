package com.alekseyld.formulaconverter.repository.base;

import com.alekseyld.formulaconverter.entity.Entity;

/**
 * Created by Alekseyld on 04.09.2016.
 */

public interface EntityRepository {

    boolean saveEntity(Entity entity);
    Entity getEntity();

}
