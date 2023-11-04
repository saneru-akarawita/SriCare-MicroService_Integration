package com.sricare.microservices.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "billing_info")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @Column(name = "period")
    private String period;

    @Column(name = "previous_due_amount")
    private Double previousDueAmount;

    @Column(name = "charges_for_period")
    private Double chargesForPeriod;

    @Column(name = "pay_before")
    private Date payBefore;

}
