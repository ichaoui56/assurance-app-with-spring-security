package org.example.lifesafe.service;

import org.example.lifesafe.model.entities.Contract;

import java.util.Optional;

public interface IContractService {
    void addContract(Contract contract);

    Optional<Contract> findById(int id);
}
