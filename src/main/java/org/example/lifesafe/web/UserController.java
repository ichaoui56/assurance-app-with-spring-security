package org.example.lifesafe.web;

import jakarta.servlet.http.HttpSession;
import org.example.lifesafe.model.entity.*;
import org.example.lifesafe.model.enums.InsuranceType;
import org.example.lifesafe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/profile")
    @Transactional
    public String showProfile(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            Optional<User> userWithDetailsOpt = userService.findUserWithInsurances(loggedInUser.getId());

            if (userWithDetailsOpt.isPresent()) {
                User userWithDetails = userWithDetailsOpt.get();
                model.addAttribute("user", userWithDetails);

                List<Insurance> insurances = userWithDetails.getInsurances();
                model.addAttribute("insurances", insurances);

                List<Contract> contracts = insurances.stream()
                        .flatMap(insurance -> insurance.getDevisList().stream()
                                .map(devis -> {
                                    Contract contract = devis.getContract();
                                    if (insurance instanceof Automobile) {
                                        contract.getDevis().getInsurance().setType(InsuranceType.Automobile);
                                    } else if (insurance instanceof Health) {
                                        contract.getDevis().getInsurance().setType(InsuranceType.Health);
                                    } else if (insurance instanceof Housing) {
                                        contract.getDevis().getInsurance().setType(InsuranceType.Housing);
                                    }
                                    return contract;
                                }))
                        .collect(Collectors.toList());

                model.addAttribute("contracts", contracts);

                return "profile";
            }
        }

        return "redirect:/auth/login";
    }
}
