package org.example.lifesafe.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    private String id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Timestamp createdAt;

    private Timestamp lastAccessedAt;

    private boolean expired;
}
