package com.alekseyld.formulaconverter.repository;

import com.alekseyld.formulaconverter.entity.Formula;
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
    public boolean saveEntity(Formula formula) {
        return false;
    }

    @Override
    public Formula getEntity() {
        return null;
    }

}
