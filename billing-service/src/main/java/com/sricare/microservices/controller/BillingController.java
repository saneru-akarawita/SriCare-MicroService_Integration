package com.sricare.microservices.controller;

import com.sricare.microservices.dto.BillDTO;
import com.sricare.microservices.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/billing")
public class BillingController {

    private final BillingService billingService;

    @GetMapping("/unpaid/{userId}")
    public Object getUnpaidBillsByUserId(@PathVariable Long userId) {
        return billingService.getUnpaidBillsByUserId(userId);
    }

    @GetMapping("/get-bill/{id}")
    public BillDTO getBillById(@PathVariable Long id) {
        return billingService.getBillById(id);
    }

    @PostMapping("/pay/{id}")
    public String payBill(@PathVariable Long id) {
        return billingService.payBill(id);
    }

    @RequestMapping("/get-all-bills")
    public List<BillDTO> getAllBills() {
        return billingService.getAllBills();
    }
}
