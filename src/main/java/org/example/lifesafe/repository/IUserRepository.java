package org.example.lifesafe.repository;

import org.example.lifesafe.model.entity.User;

import java.util.Optional;

public interface IUserRepository extends IDefaultRepository<User> {
    Optional<User> findByEmail(String email);
}
