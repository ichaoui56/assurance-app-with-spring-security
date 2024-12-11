package org.example.lifesafe.util;

import org.example.lifesafe.model.entities.Automobile;
import org.example.lifesafe.model.entities.Health;
import org.example.lifesafe.model.entities.Housing;
import org.example.lifesafe.model.enums.CarUse;
import org.example.lifesafe.model.enums.CoverType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class CalculateDevis {

    private static final double BASE_PRICE_FOR_AUTOMOBILE = 500.0;
    private static final double UNDER_25_YEARS_AGE_SURCHARGE = 0.10;
    private static final double LUXURY_VEHICLE_SURCHARGE = 0.15;
    private static final double PROFESSIONAL_USE_SURCHARGE = 0.10;
    private static final double NO_ACCIDENT_DISCOUNT = 0.20;
    private static final double ACCIDENT_SURCHARGE = 0.10;

    private static final double BASE_PRICE_FOR_HOUSING = 300.0;
    private static final double HOUSING_TYPE_IS_MAISON = 0.02;
    private static final double LOCATED_IN_RISK_ZONE = 0.05;
    private static final double VALUE_MORE_THAN_200000_MAD = 0.05;
    private static final double IF_HE_HAVE_SECURITY_SYSTEM = 0.15;
    private static final double IF_HE_HAVE_NOT_SECURITY_SYSTEM = 0.15;

    private static final double BASE_PRICE_FOR_HEALTH = 150.0;
    private static final double OLDER_THAN_60_YEARS_OLD = 0.20;
    private static final double HAS_HISTORY_CHRONIC_ILLNESSES = 0.20;
    private static final double HAS_BASIC_COVER_TYPE = 0.10;
    private static final double HAS_PREMIUM_COVER_TYPE = 0.05;

    public double calculateAutomobileDevis(Automobile automobile) {
        double totalQuote = BASE_PRICE_FOR_AUTOMOBILE;

        if (automobile.getDriverAge() < 25) {
            totalQuote += BASE_PRICE_FOR_AUTOMOBILE * UNDER_25_YEARS_AGE_SURCHARGE;
        }

        if ("luxe".equalsIgnoreCase(automobile.getCar().getType())) {
            totalQuote += BASE_PRICE_FOR_AUTOMOBILE * LUXURY_VEHICLE_SURCHARGE;
        }

        if (automobile.getCarUse() == CarUse.Professional) {
            totalQuote += BASE_PRICE_FOR_AUTOMOBILE * PROFESSIONAL_USE_SURCHARGE;
        }

        if (automobile.getLastAccidentDate() != null) {
            int yearsSinceLastAccident = (int) ChronoUnit.YEARS.between(automobile.getLastAccidentDate(), LocalDate.now());
            if (yearsSinceLastAccident < 3) {
                totalQuote += BASE_PRICE_FOR_AUTOMOBILE * ACCIDENT_SURCHARGE;
            } else {
                totalQuote -= BASE_PRICE_FOR_AUTOMOBILE * NO_ACCIDENT_DISCOUNT;
            }
        }

        return totalQuote;
    }

    public double calculateHousingDevis(Housing housing) {
        double totalQuote = BASE_PRICE_FOR_HOUSING;

        if (housing.getHomeType().equalsIgnoreCase("Maison")) {
            totalQuote += BASE_PRICE_FOR_HOUSING * HOUSING_TYPE_IS_MAISON;
        }

        if (housing.getLocation().equalsIgnoreCase("RiskZone")) {
            totalQuote += BASE_PRICE_FOR_HOUSING * LOCATED_IN_RISK_ZONE;
        }

        if (housing.getHomeValue() > 200000) {
            totalQuote += BASE_PRICE_FOR_HOUSING * VALUE_MORE_THAN_200000_MAD;
        }

        if (!housing.getSecuritySystem().equalsIgnoreCase("Aucun")) {
            totalQuote -= BASE_PRICE_FOR_HOUSING * IF_HE_HAVE_SECURITY_SYSTEM;
        } else {
            totalQuote += BASE_PRICE_FOR_HOUSING * IF_HE_HAVE_NOT_SECURITY_SYSTEM;
        }

        return totalQuote;
    }

    public double calculateHealthDevis(Health health){
        double totaleQuote = BASE_PRICE_FOR_HEALTH;

        if (health.getAge() > 60){
            totaleQuote += BASE_PRICE_FOR_HEALTH * OLDER_THAN_60_YEARS_OLD;
        }

        if (health.getChronicIllness()){
            totaleQuote += BASE_PRICE_FOR_HEALTH * HAS_HISTORY_CHRONIC_ILLNESSES;
        }

        if (health.getMedicalCoverageType().equals(CoverType.Basic)){
            totaleQuote -= BASE_PRICE_FOR_HEALTH * HAS_BASIC_COVER_TYPE;
        } else {
            totaleQuote += BASE_PRICE_FOR_HEALTH * HAS_PREMIUM_COVER_TYPE;
        }

        return totaleQuote;
    }

}
