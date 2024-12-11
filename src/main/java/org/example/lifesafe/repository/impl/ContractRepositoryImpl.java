package org.example.lifesafe.repository.impl;

import jakarta.persistence.TypedQuery;
import org.example.lifesafe.model.entity.Contract;
import org.example.lifesafe.model.enums.InsuranceType;
import org.example.lifesafe.repository.IContractRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContractRepositoryImpl extends DefaultRepositoryImpl<Contract> implements IContractRepository {

    public ContractRepositoryImpl(){
        super(Contract.class);
    }

    @Override
    public boolean userHasActiveInsurance(int userId, InsuranceType insuranceType) {
        String query = "SELECT c FROM Contract c " +
                "JOIN c.devis d " +
                "JOIN d.insurance i " +
                "WHERE i.user.id = :userId " +
                "AND i.type = :insuranceType " +
                "AND c.isActive = true";
        TypedQuery<Contract> typedQuery = entityManager.createQuery(query, Contract.class);
        typedQuery.setParameter("userId", userId);
        typedQuery.setParameter("insuranceType", insuranceType); // Use the enum here

        List<Contract> result = typedQuery.getResultList();
        return !result.isEmpty();
    }
}
