package org.example.lifesafe.repository;

import org.example.lifesafe.model.entities.Contract;
import org.example.lifesafe.model.entities.Insurance;
import org.example.lifesafe.model.entities.User;
import org.example.lifesafe.model.enums.InsuranceType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IDefaultRepository<Entity> {
    boolean create ( Entity entity );
    boolean update ( Entity entity );
    boolean delete ( Entity entity );
    Optional<Entity> findById ( int id );
    List<Entity> findAll ();
}
