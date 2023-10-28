package com.sricare.microservices.controller;

import com.sricare.microservices.dto.BillDTO;
import com.sricare.microservices.service.BillingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping("/unpaid/{userId}")
    public List<BillDTO> getUnpaidBillsByUserId(@PathVariable Long userId) {
        return billingService.getUnpaidBillsByUserId(userId);
    }

    @GetMapping("/get-bill/{id}")
    public BillDTO getBillById(@PathVariable Long id) {
        return billingService.getBillById(id);
    }

    @PostMapping("/pay/{id}")
    public void payBill(@PathVariable Long id) {
        billingService.payBill(id);
    }
}
