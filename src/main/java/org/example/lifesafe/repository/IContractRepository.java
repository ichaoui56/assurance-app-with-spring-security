package org.example.lifesafe.repository;

import org.example.lifesafe.model.entities.Contract;
import org.example.lifesafe.model.enums.InsuranceType;

public interface IContractRepository extends IDefaultRepository<Contract>{
    boolean userHasActiveInsurance(int userId, InsuranceType insuranceType);
}
