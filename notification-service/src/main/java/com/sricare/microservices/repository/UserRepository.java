package com.sricare.microservices.repository;

import com.sricare.microservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom repository methods, if needed
}
