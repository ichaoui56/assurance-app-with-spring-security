package org.example.lifesafe.service.impl;

import org.example.lifesafe.model.entities.Devis;
import org.example.lifesafe.repository.IDevisRepository;
import org.example.lifesafe.service.IDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DevisServiceImpl implements IDevisService {
    private final IDevisRepository devisRepository;

    @Autowired
    public DevisServiceImpl(IDevisRepository devisRepository) {
        this.devisRepository = devisRepository;
    }

    @Override
    public void addDevis(Devis devis){
        devisRepository.create(devis);
    }

    @Override
    public Optional<Devis> findById(int id) {
        return devisRepository.findById(id);
    }

    @Override
    public void updateDevis(Devis devis){
        devisRepository.update(devis);
    }

}
