package com.sricare.ringtone.service;

import com.sricare.ringtone.dto.RingingToneRequestDTO;
import com.sricare.ringtone.model.RingingTone;
import com.sricare.ringtone.model.UserRingtones;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface RingingToneService {
    void saveRingingTone(@ModelAttribute RingingToneRequestDTO ringingToneRequestDTO);
    RingingTone getRingingToneById(Long id);

    void activateRingtone(Long userId,Long ringtoneID);

    void deactivateRingtone(Long ringtoneId);

    UserRingtones getActivatedRingTone(Long userId);

    List<RingingTone> getAllRingingTone();
}
