package com.sricare.ringtone.repository;

import com.sricare.ringtone.model.RingingTone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RingingToneRepository extends JpaRepository<RingingTone,Long> {
}
