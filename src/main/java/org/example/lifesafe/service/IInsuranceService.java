package org.example.lifesafe.service;

import org.example.lifesafe.model.entities.*;
import org.example.lifesafe.model.enums.InsuranceType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IInsuranceService {
    void addInsurance(Insurance insurance);
    double calculateAutomobileDevis(Automobile automobile);
    double calculateHousingDevis(Housing housing);
    double calculateHealthDevis(Health health);

    boolean checkUserHasActiveInsurance(int userId, InsuranceType insuranceType);

    boolean checkUserHasActiveAnyInsurance(int userId);
}
