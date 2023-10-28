package com.sricare.ringtone.controller;

import com.sricare.ringtone.dto.RingingToneRequestDTO;
import com.sricare.ringtone.model.RingingTone;
import com.sricare.ringtone.model.UserRingtones;
import com.sricare.ringtone.service.RingingToneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/ringing-tones")
public class RingingToneController {
    private final RingingToneService ringingToneService;

    @PostMapping("/add")
    public void addRingingTone(@ModelAttribute RingingToneRequestDTO ringingToneRequestDTO){
        ringingToneService.saveRingingTone(ringingToneRequestDTO);
    }

    @PostMapping("activate/{user_id}")
    public void activateRingTone(@PathVariable Long user_id,
            @RequestBody Long ringTone_id){
        ringingToneService.activateRingtone(user_id,ringTone_id);
    }

    @DeleteMapping("/deactivate/{ringtoneId}")
    public void deactivateRingTone(@PathVariable Long ringtoneId){
        ringingToneService.deactivateRingtone(ringtoneId);
    }

    @GetMapping("/activated/{userId}")
    public UserRingtones getActivatedRingTone(@PathVariable Long userId){
        return ringingToneService.getActivatedRingTone(userId);
    }

    @GetMapping("/{id}")
    public RingingTone getRingingToneById(@PathVariable("id") Long id){
        return ringingToneService.getRingingToneById(id);

    }

    @GetMapping("/all")
    public List<RingingTone> getAllRingingTones(){
        return ringingToneService.getAllRingingTone();
    }
}
