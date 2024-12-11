package org.example.lifesafe.repository.impl;

import org.example.lifesafe.model.entities.Insurance;
import org.example.lifesafe.repository.IInsuranceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class InsuranceRepositoryImpl extends DefaultRepositoryImpl<Insurance> implements IInsuranceRepository {

    public InsuranceRepositoryImpl() {
        super(Insurance.class);
    }
}
