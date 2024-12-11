package org.example.lifesafe.service;

import org.example.lifesafe.model.entity.*;
import org.example.lifesafe.model.enums.InsuranceType;

public interface IInsuranceService {
    void addInsurance(Insurance insurance);
    double calculateAutomobileDevis(Automobile automobile);
    double calculateHousingDevis(Housing housing);
    double calculateHealthDevis(Health health);

    boolean checkUserHasActiveInsurance(int userId, InsuranceType insuranceType);

    boolean checkUserHasActiveAnyInsurance(int userId);
}
