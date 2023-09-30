package com.sricare.microservices.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_mobile_numbers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileNumbers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mobileNumber;

}
