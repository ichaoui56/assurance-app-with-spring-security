package org.example.lifesafe.web;

import org.example.lifesafe.model.entities.Contract;
import org.example.lifesafe.model.entities.Devis;
import org.example.lifesafe.service.IContractService;
import org.example.lifesafe.service.IDevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/contract")
public class ContractController {

    private final IContractService contractService;
    private final IDevisService devisService;

    @Autowired
    public ContractController(IContractService contractService, IDevisService devisService) {
        this.contractService = contractService;
        this.devisService = devisService;
    }

    @GetMapping("/addForm/{devisId}")
    public String showContractForm(@PathVariable("devisId") int devisId, Model model) {
        model.addAttribute("devisId", devisId);
        return "forms/contractForm";
    }

    @GetMapping("/details")
    public String showContractDetails(@RequestParam("contractId") int contractId, Model model) {
        Optional<Contract> contractOpt = contractService.findById(contractId);

        if (contractOpt.isPresent()) {
            Contract contract = contractOpt.get();
            Devis devis = contract.getDevis();

            model.addAttribute("contract", contract);
            model.addAttribute("devis", devis);
            return "contractDetails";
        }

        return "redirect:/error";
    }

    @PostMapping("/add")
    public String addContract(@RequestParam("devisId") int devisId,
                              @RequestParam("document") MultipartFile document,
                              HttpSession session) {
        Optional<Devis> devisOpt = devisService.findById(devisId);
        if (devisOpt.isPresent()) {
            Devis devis = devisOpt.get();
            Contract contract = new Contract();
            contract.setSubscriptionDate(LocalDate.now());
            contract.setExpirationDate(LocalDate.now().plusMonths(1));
            contract.setActive(true);
            contract.setDevis(devis);

            try {

                String filePath = "/Users/mac/Documents/GitHub/LifeSafe/src/main/webapp/files/" + document.getOriginalFilename();
                File file = new File(filePath);
                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(document.getBytes());
                    contract.setDocument(filePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/error";
            }


            contractService.addContract(contract);
            return "redirect:/";
        } else {
            return "redirect:/devis,";
        }
    }
}
