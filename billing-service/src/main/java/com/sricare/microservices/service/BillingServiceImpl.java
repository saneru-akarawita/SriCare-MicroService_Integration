package com.sricare.microservices.service;

import com.sricare.microservices.dto.BillDTO;
import com.sricare.microservices.model.Bill;
import com.sricare.microservices.repository.BillRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillingServiceImpl implements BillingService {

    private final BillRepository billRepository;
    //private EmailService emailService;

    public BillingServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public List<BillDTO> getUnpaidBillsByUserId(Long userId) {
        List<Bill> unpaidBills = billRepository.findByUserIdAndStatus(userId, "not-paid");
        return unpaidBills.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BillDTO getBillById(Long id) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        return optionalBill.map(this::convertToDTO).orElse(null);
    }

    @Override
    public void payBill(Long id) {
        Optional<Bill> optionalBill = billRepository.findById(id);
        if (optionalBill.isPresent()) {
            Bill bill = optionalBill.get();
            bill.setStatus("paid");
            billRepository.save(bill);
            //emailService.sendPaymentConfirmationEmail(user.getEmail())
        }
    }

    private BillDTO convertToDTO(Bill bill) {
        BillDTO dto = new BillDTO();
        BeanUtils.copyProperties(bill, dto);
        return dto;
    }
}
