package com.sricare.microservices.service;

import com.sricare.microservices.dto.BillDTO;
import com.sricare.microservices.dto.NotificationDTO;
import com.sricare.microservices.dto.UserDto;
import com.sricare.microservices.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BillingService {

    private final BillRepository billRepository;

    private final WebClient.Builder webClientBuilder;

    public Object getUnpaidBillsByUserId(Long userId)
    {

        return billRepository.findAllByUserIdAndStatus(userId, "Not paid");
    }

    public BillDTO getBillById(Long id) {
        var bill = billRepository.findById(id).orElseThrow();

        return BillDTO.builder()
                .id(bill.getId())
                .status(bill.getStatus())
                .period(bill.getPeriod())
                .previousDueAmount(bill.getPreviousDueAmount())
                .chargesForPeriod(bill.getChargesForPeriod())
                .payBefore(bill.getPayBefore())
                .build();
    }

    public String payBill(Long id) {
        var bill = billRepository.findById(id).orElseThrow();

        bill.setStatus("Paid");
        billRepository.save(bill);

        var user = webClientBuilder.build()
                .get()
                .uri("http://Authentication-Service/api/v1/auth/getUserById/" + bill.getUserId())
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();

        assert user != null;
        NotificationDTO notificationDTO = NotificationDTO.builder()
                        .to(user.getEmail())
                        .subject("Bill paid")
                        .text("Bill with id " + id + " has been paid").build();

        webClientBuilder.build()
                .post()
                .uri("http://Notification-Service/api/v1/notification/send-email")
                .bodyValue(notificationDTO)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return "Bill paid successfully";
    }

    public List<BillDTO> getAllBills() {
        return billRepository.findAll().stream().map(bill -> BillDTO.builder()
                .id(bill.getId())
                .status(bill.getStatus())
                .period(bill.getPeriod())
                .previousDueAmount(bill.getPreviousDueAmount())
                .chargesForPeriod(bill.getChargesForPeriod())
                .payBefore(bill.getPayBefore())
                .build()).toList();
    }
}
