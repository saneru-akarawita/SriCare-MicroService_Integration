package com.sricare.microservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "billing_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userid")
    private Long userId;

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

    // Constructors, getters, and setters
}
