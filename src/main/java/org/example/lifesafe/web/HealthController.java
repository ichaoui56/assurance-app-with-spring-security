package org.example.lifesafe.web;

import org.example.lifesafe.model.entities.*;
import org.example.lifesafe.model.enums.CarUse;
import org.example.lifesafe.model.enums.CoverType;
import org.example.lifesafe.model.enums.DevisStatus;
import org.example.lifesafe.model.enums.InsuranceType;
import org.example.lifesafe.service.ICarService;
import org.example.lifesafe.service.IDevisService;
import org.example.lifesafe.service.IInsuranceService;
import org.example.lifesafe.util.CalculateDevis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/health")
public class HealthController {

    private final IInsuranceService insuranceService;
    private final IDevisService devisService;
    private final CalculateDevis calculateDevis;

    @Autowired
    public HealthController(IInsuranceService insuranceService,
                            IDevisService devisService,
                            CalculateDevis calculateDevis) {
        this.insuranceService = insuranceService;
        this.devisService = devisService;
        this.calculateDevis = calculateDevis;
    }

    @GetMapping
    public String showInsuranceHealth() {
        return "health";
    }

    @GetMapping("/checkInsurance")
    public String checkInsurance(HttpSession session, Model model) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/auth/login";
        }
        boolean hasActiveInsurance = false;

        if (insuranceService.checkUserHasActiveInsurance(loggedInUser.getId(), InsuranceType.Health)) {
            hasActiveInsurance = true;
            model.addAttribute("message", "You already have an active health insurance contract.");
            model.addAttribute("alertClass", "alert-danger");
        }

        if (!hasActiveInsurance) {
            model.addAttribute("message", "You do not have any active insurance contracts.");
            model.addAttribute("alertClass", "alert-success");
            return "redirect:/health/addForm";
        }

        return "redirect:/user/profile";
    }

    @GetMapping("/addForm")
    public String showInsuranceHealthForm(Model model) {
        model.addAttribute("coverTypes", CoverType.values());
        return "forms/healthForm";
    }

    @PostMapping("/addInsurance")
    public String addInsurance(@ModelAttribute Health health, Model model, HttpSession session) {

        User loggedInUser = (User) session.getAttribute("loggedInUser");

        health.setType(InsuranceType.Health);
        health.setQuoteAmount(150);
        health.setUser(loggedInUser);
        insuranceService.addInsurance(health);

        double totalQuote = insuranceService.calculateHealthDevis(health);

        System.out.println(totalQuote);
        Devis devis = new Devis(health, LocalDate.now(), totalQuote, DevisStatus.Pending);
        devisService.addDevis(devis);

        return "redirect:/devis/" + devis.getId();

    }
}
