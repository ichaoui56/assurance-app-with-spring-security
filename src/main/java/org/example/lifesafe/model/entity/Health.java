package org.example.lifesafe.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.example.lifesafe.model.enums.InsuranceType;
import org.example.lifesafe.model.enums.CoverType;

@Entity
@Table(name = "health_insurances")
public class Health  extends Insurance{

    @Column(nullable = false)
    private int age;

    @Column(name = "has_chronic_illness", columnDefinition = "boolean default false")
    private boolean chronicIllness;

    @Column(name="medical_coverage_type", nullable = false)
    private CoverType medicalCoverageType;

    public Health(){}

    public Health(double quoteAmount, InsuranceType type, int age, boolean chronicIllness, CoverType medicalCoverageType, User user){
        super(150,type, user);
        this.age = age;
        this.chronicIllness = chronicIllness;
        this.medicalCoverageType = medicalCoverageType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getChronicIllness() {
        return chronicIllness;
    }

    public void setChronicIllness(boolean chronicIllness) {
        this.chronicIllness = chronicIllness;
    }

    public CoverType getMedicalCoverageType() {
        return medicalCoverageType;
    }

    public void setMedicalCoverageType(CoverType medicalCoverageType) {
        this.medicalCoverageType = medicalCoverageType;
    }
}