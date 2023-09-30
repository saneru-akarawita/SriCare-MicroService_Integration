package com.sricare.ringtone.service;

import com.sricare.ringtone.dto.RingingToneRequestDTO;
import com.sricare.ringtone.model.RingingTone;
import com.sricare.ringtone.repository.RingingToneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RingingToneServiceImpl implements RingingToneService{
    private final RingingToneRepository ringingToneRepository;
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
    public List<RingingTone> getAllRingingTone() {
        return ringingToneRepository.findAll();
    }
}
