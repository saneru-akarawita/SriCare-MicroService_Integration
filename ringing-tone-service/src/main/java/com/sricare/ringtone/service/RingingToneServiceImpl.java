package com.sricare.ringtone.service;

import com.sricare.ringtone.dto.RingingToneRequestDTO;
import com.sricare.ringtone.model.RingingTone;
import com.sricare.ringtone.model.UserRingtones;
import com.sricare.ringtone.repository.RingingToneRepository;
import com.sricare.ringtone.repository.UserRingtoneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RingingToneServiceImpl implements RingingToneService{
    private final RingingToneRepository ringingToneRepository;
    private final UserRingtoneRepository userRingtoneRepository;
    @Override
    public void saveRingingTone(RingingToneRequestDTO ringingToneRequestDTO) {
        RingingTone ringingTone=RingingTone.builder()
                .name(ringingToneRequestDTO.getName())
                .build();
        ringingToneRepository.save(ringingTone);
    }

    @Override
    public RingingTone getRingingToneById(Long id) {
        Optional<RingingTone> ringingTone= ringingToneRepository.findById(id);
        return ringingTone.orElse(null);
    }

    @Override
    public void activateRingtone(Long userId, Long ringtoneID) {
        Optional<RingingTone> ringingTone=
                ringingToneRepository.findById(ringtoneID);
        if (ringingTone.isPresent()){
            UserRingtones userRingtones=UserRingtones.builder()
                    .userId(userId)
                    .ringingTone(ringingTone.get())
                    .build();
            userRingtoneRepository.save(userRingtones);
        }
    }

    @Override
    public void deactivateRingtone(Long ringtoneId) {
        Optional<UserRingtones> userRingtones=
                userRingtoneRepository.findById(ringtoneId);
        userRingtones.ifPresent(userRingtoneRepository::delete);
    }

    @Override
    public UserRingtones getActivatedRingTone(Long userId) {
        return userRingtoneRepository.findByUserId(userId);
    }

    @Override
    public List<RingingTone> getAllRingingTone() {
        return ringingToneRepository.findAll();
    }
}
