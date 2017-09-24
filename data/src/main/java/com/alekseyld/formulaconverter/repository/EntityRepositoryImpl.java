package com.alekseyld.formulaconverter.repository;

import com.alekseyld.formulaconverter.entity.Entity;
import com.alekseyld.formulaconverter.repository.base.EntityRepository;

import javax.inject.Inject;

/**
 * Created by Alekseyld on 04.09.2016.
 */
public class EntityRepositoryImpl implements EntityRepository {

    @Inject
    EntityRepositoryImpl(){
    }

    @Override
    public boolean saveEntity(Entity entity) {
        return false;
    }

    @Override
    public Entity getEntity() {
        return null;
    }

}
