package org.example.lifesafe.repository.impl;

import org.example.lifesafe.model.entity.Devis;
import org.example.lifesafe.repository.IDevisRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DevisRepositoryImpl extends DefaultRepositoryImpl<Devis> implements IDevisRepository {

    public DevisRepositoryImpl() {
        super(Devis.class);
    }
}
