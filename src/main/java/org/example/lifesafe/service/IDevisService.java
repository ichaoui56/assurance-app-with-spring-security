package org.example.lifesafe.service;

import org.example.lifesafe.model.entities.Devis;

import java.util.Optional;

public interface IDevisService {
    void addDevis(Devis devis);

    Optional<Devis> findById(int id);

    void updateDevis(Devis devis);
}
