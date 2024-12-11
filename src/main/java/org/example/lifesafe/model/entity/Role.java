package org.example.lifesafe.model.entity;

import jakarta.persistence.*;
import org.example.lifesafe.model.enums.RoleType;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleType roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
