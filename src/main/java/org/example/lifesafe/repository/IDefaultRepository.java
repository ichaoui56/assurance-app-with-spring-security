package org.example.lifesafe.repository;

import java.util.List;
import java.util.Optional;

public interface IDefaultRepository<Entity> {
    boolean create ( Entity entity );
    boolean update ( Entity entity );
    boolean delete ( Entity entity );
    Optional<Entity> findById ( int id );
    List<Entity> findAll ();
}
