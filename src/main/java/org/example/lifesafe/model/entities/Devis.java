package org.example.lifesafe.model.entities;

import jakarta.persistence.*;
import org.example.lifesafe.model.enums.DevisStatus;
import java.time.LocalDate;

@Entity
@Table(name = "devis")
public class Devis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate requestDate;

    @Column(nullable = false)
    private double calculatedQuote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DevisStatus status;

    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false)
    private Insurance insurance;

    @OneToOne(mappedBy = "devis", cascade = CascadeType.ALL)
    private Contract contract;

    public Devis() {}

    public Devis(Insurance insurance, LocalDate requestDate, double calculatedQuote, DevisStatus status) {
        this.insurance = insurance;
        this.requestDate = requestDate;
        this.calculatedQuote = calculatedQuote;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public double getCalculatedQuote() {
        return calculatedQuote;
    }

    public void setCalculatedQuote(double calculatedQuote) {
        this.calculatedQuote = calculatedQuote;
    }

    public DevisStatus getStatus() {
        return status;
    }

    public void setStatus(DevisStatus status) {
        this.status = status;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
