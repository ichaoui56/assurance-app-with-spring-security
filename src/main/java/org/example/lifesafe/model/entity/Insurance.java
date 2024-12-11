package org.example.lifesafe.model.entity;

import jakarta.persistence.*;
import org.example.lifesafe.model.enums.InsuranceType;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "insurances")
public abstract class Insurance {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double quoteAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InsuranceType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL)
    private List<Devis> devisList;

    public Insurance() {}

    public Insurance(double quoteAmount, InsuranceType type, User user) {
        this.quoteAmount = quoteAmount;
        this.type = type;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuoteAmount() {
        return quoteAmount;
    }

    public void setQuoteAmount(double quoteAmount) {
        this.quoteAmount = quoteAmount;
    }

    public InsuranceType getType() {
        return type;
    }

    public void setType(InsuranceType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Devis> getDevisList() {
        return devisList;
    }

    public void setDevisList(List<Devis> devisList) {
        this.devisList = devisList;
    }
}
