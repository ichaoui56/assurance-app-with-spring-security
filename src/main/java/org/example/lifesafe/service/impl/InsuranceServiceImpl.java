package org.example.lifesafe.service.impl;

import org.example.lifesafe.model.entity.*;
import org.example.lifesafe.model.enums.InsuranceType;
import org.example.lifesafe.repository.IContractRepository;
import org.example.lifesafe.repository.IInsuranceRepository;
import org.example.lifesafe.service.IInsuranceService;
import org.example.lifesafe.util.CalculateDevis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements IInsuranceService {

    private final IInsuranceRepository insuranceRepository;
    private final CalculateDevis calculateDevis;
    private final IContractRepository contractRepository;

    @Autowired
    public InsuranceServiceImpl(IInsuranceRepository insuranceRepository, CalculateDevis calculateDevis, IContractRepository contractRepository){
        this.insuranceRepository = insuranceRepository;
        this.calculateDevis = calculateDevis;
        this.contractRepository = contractRepository;
    }

    @Override
    public void addInsurance(Insurance insurance){
        insuranceRepository.create(insurance);
    }

    @Override
    public double calculateAutomobileDevis(Automobile automobile) {
        return calculateDevis.calculateAutomobileDevis(automobile);
    }

    @Override
    public double calculateHousingDevis(Housing housing) {
        return calculateDevis.calculateHousingDevis(housing);
    }

    @Override
    public double calculateHealthDevis(Health health) {
        return calculateDevis.calculateHealthDevis(health);
    }

    @Override
    public boolean checkUserHasActiveInsurance(int userId, InsuranceType insuranceType) {
        return contractRepository.userHasActiveInsurance(userId, insuranceType);
    }

    @Override
    public boolean checkUserHasActiveAnyInsurance(int userId) {
        InsuranceType[] insuranceTypes = InsuranceType.values();
        for (InsuranceType type : insuranceTypes) {
            if (checkUserHasActiveInsurance(userId, type)) {
                return true;
            }
        }
        return false;
    }
}
