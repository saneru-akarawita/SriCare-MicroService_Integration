package com.sricare.ringtone.repository;

import com.sricare.ringtone.model.UserRingtones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRingtoneRepository extends JpaRepository<UserRingtones,
        Long> {
    UserRingtones findByUserId(Long userId);
}
