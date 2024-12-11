package org.example.lifesafe.model.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="subscription_date", nullable = false)
    private LocalDate subscriptionDate;

    @Column(name="expiration_date")
    private LocalDate expirationDate;

    @Column(nullable = false)
    private String document;

    @Column(nullable = false)
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "devis_id", nullable = false)
    private Devis devis;

    public Contract() {}

    public Contract(LocalDate subscriptionDate, LocalDate expirationDate, String document, boolean isActive, Devis devis) {
        this.subscriptionDate = subscriptionDate;
        this.expirationDate = expirationDate;
        this.document = document;
        this.isActive = isActive;
        this.devis = devis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDate subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }
}
