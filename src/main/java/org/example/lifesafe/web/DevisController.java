package org.example.lifesafe.web;

import org.example.lifesafe.model.entity.*;
import org.example.lifesafe.model.enums.CarUse;
import org.example.lifesafe.model.enums.CoverType;
import org.example.lifesafe.model.enums.DevisStatus;
import org.example.lifesafe.model.enums.InsuranceType;
import org.example.lifesafe.service.ICarService;
import org.example.lifesafe.service.IDevisService;
import org.example.lifesafe.service.IInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/devis")
public class DevisController {

    private final IDevisService devisService;
    private final IInsuranceService insuranceService;
    private final ICarService carService;

    @Autowired
    public DevisController(IDevisService devisService, IInsuranceService insuranceService, ICarService carService) {
        this.devisService = devisService;
        this.insuranceService = insuranceService;
        this.carService = carService;
    }
//    @GetMapping
//    public String listDevis(HttpSession session, Model model) {
//        User loggedInUser = (User) session.getAttribute("loggedInUser");
//
//        if (loggedInUser != null) {
//            List<Devis> devisList = devisService.findByUser(loggedInUser);
//            model.addAttribute("devisList", devisList);
//            return "devis/list";
//        } else {
//            return "redirect:/login"; // Redirect to login if not authenticated
//        }
//    }

    @GetMapping("/{id}")
    public String showDevisDetails(@PathVariable("id") int id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Optional<Devis> optionalDevis = devisService.findById(id);
        if (optionalDevis.isPresent()) {
            Devis devis = optionalDevis.get();
            if (devis.getInsurance().getUser().getEmail().equals(loggedInUser.getEmail())) {
                model.addAttribute("devis", devis);
                return "devisDetails";
            } else {
                return "redirect:/devis/error";
            }
        } else {
            return "redirect:/devis";
        }
    }

    @PostMapping("/{id}/accept")
    public String acceptDevis(@PathVariable("id") int id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            Optional<Devis> devisOpt = devisService.findById(id);
            if (devisOpt.isPresent()) {
                Devis devis = devisOpt.get();
                if (devis.getInsurance().getUser().getEmail().equals(loggedInUser.getEmail())) {
                    devis.setStatus(DevisStatus.Accepted);
                    devisService.updateDevis(devis);
                    return "redirect:/contract/addForm/" + devis.getId();
                }
            } else {
                return "redirect:/";
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/updateForm/{id}")
    public String showInsuranceHousingForm(Model model, @PathVariable String id) {
        return "forms/housingUpdateForm";
    }

    @PostMapping("/{id}/reject")
    public String rejectDevis(@PathVariable("id") int id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Optional<Devis> devisOpt = devisService.findById(id);
            if (devisOpt.isPresent()) {
                Devis devis = devisOpt.get();
                if (devis.getInsurance().getUser().getEmail().equals(loggedInUser.getEmail())) {
                    devis.setStatus(DevisStatus.Rejected);
                    devisService.updateDevis(devis);
                    return "redirect:/";
                } else {
                    return "redirect:/login";
                }
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/{id}/updateForm")
    public String showInsuranceUpdateForm(@PathVariable("id") int id, Model model, HttpSession session) {
        Optional<Devis> optionalDevis = devisService.findById(id);
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        List<Car> cars = carService.findAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("carUses", CarUse.values());

        model.addAttribute("coverTypes", CoverType.values());

        if (optionalDevis.isPresent() && loggedInUser != null) {
            Devis devis = optionalDevis.get();

            if (devis.getInsurance().getUser().getEmail().equals(loggedInUser.getEmail())) {
                model.addAttribute("insurance", devis.getInsurance());
                model.addAttribute("devis", devis);

                InsuranceType type = devis.getInsurance().getType();
                return switch (type) {
                    case Automobile -> "forms/automobileUpdateForm";
                    case Housing -> "forms/housingUpdateForm";
                    case Health -> "forms/healthUpdateForm";
                    default -> "redirect:/devis/error";
                };
            }
        }
        return "redirect:/devis/error";
    }


    @PostMapping("/{devisId}/update/automobile")
    public String updateAutomobileDevis(@PathVariable("devisId") int devisId,
                                        @ModelAttribute("automobile") Automobile automobile,
                                        Model model, HttpSession session) {
        return updateSpecificDevis(devisId, automobile, model, session);
    }

    @PostMapping("/{devisId}/update/housing")
    public String updateHousingDevis(@PathVariable("devisId") int devisId,
                                     @ModelAttribute("housing") Housing housing,
                                     Model model, HttpSession session) {
        return updateSpecificDevis(devisId, housing, model, session);
    }

    @PostMapping("/{devisId}/update/health")
    public String updateHealthDevis(@PathVariable("devisId") int devisId,
                                    @ModelAttribute("health") Health health,
                                    Model model, HttpSession session) {
        return updateSpecificDevis(devisId, health, model, session);
    }

    private String updateSpecificDevis(int devisId, Insurance insurance, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        Optional<Devis> optionalDevis = devisService.findById(devisId);

        if (optionalDevis.isPresent()) {
            Devis devis = optionalDevis.get();
            double newQuoteAmount = 0;

            if (insurance.getId() == null) {
                insuranceService.addInsurance(insurance);
            }

            if (insurance instanceof Automobile) {
                newQuoteAmount = insuranceService.calculateAutomobileDevis((Automobile) insurance);
            } else if (insurance instanceof Housing) {
                newQuoteAmount = insuranceService.calculateHousingDevis((Housing) insurance);
            } else if (insurance instanceof Health) {
                newQuoteAmount = insuranceService.calculateHealthDevis((Health) insurance);
            }

            devis.setCalculatedQuote(newQuoteAmount);
            insurance.setUser(loggedInUser);
            devis.setInsurance(insurance);
            devisService.updateDevis(devis);

            model.addAttribute("devis", devis);
            return "redirect:/devis/" + devisId;
        }

        model.addAttribute("error", "Devis not found");
        return "error";
    }


}
