package com.sricare.microservices.service;

import com.sricare.microservices.dto.BillDTO;

import java.util.List;

public interface BillingService {
    List<BillDTO> getUnpaidBillsByUserId(Long userId);
    BillDTO getBillById(Long id);
    void payBill(Long id);
}

