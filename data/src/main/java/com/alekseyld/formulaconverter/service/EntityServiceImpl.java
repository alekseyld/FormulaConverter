package com.alekseyld.formulaconverter.service;

import com.alekseyld.formulaconverter.entity.Formula;
import com.alekseyld.formulaconverter.repository.base.EntityRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Alekseyld on 04.11.2016.
 */

public class EntityServiceImpl implements EntityService {

    private EntityRepository mEntityRepository;

    @Inject
    public EntityServiceImpl(EntityRepository entityRepository) {
        mEntityRepository = entityRepository;
    }

    @Override
    public Observable<Boolean> saveEntity(Formula formula) {
        return Observable.just(
                mEntityRepository.saveEntity(formula)
        );
    }

    @Override
    public Observable<Formula> getEntity() {
        return Observable.just(
                mEntityRepository.getEntity()
        );
    }
}
