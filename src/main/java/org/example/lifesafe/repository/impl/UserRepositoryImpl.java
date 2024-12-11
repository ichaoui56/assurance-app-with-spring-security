package org.example.lifesafe.repository.impl;

import org.example.lifesafe.model.entity.User;
import org.example.lifesafe.repository.IUserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl extends DefaultRepositoryImpl<User> implements IUserRepository {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email) {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
