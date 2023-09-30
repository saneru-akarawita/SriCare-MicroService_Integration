package com.sricare.microservices.repository;

import com.sricare.microservices.model.MobileNumbers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MobileNumberRepository extends JpaRepository<MobileNumbers, String> {

    List<MobileNumbers> findByMobileNumber(String mobileNumber);

    boolean existsByMobileNumber(String mobileNumber);
}
