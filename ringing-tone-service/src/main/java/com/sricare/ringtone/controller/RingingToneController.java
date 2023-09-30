package com.sricare.ringtone.controller;

import com.sricare.ringtone.dto.RingingToneRequestDTO;
import com.sricare.ringtone.model.RingingTone;
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

    @GetMapping("/{id}")
    public RingingTone getRingingToneById(@PathVariable("id") Long id){
        return ringingToneService.getRingingToneById(id);

    }

    @GetMapping("/all")
    public List<RingingTone> getAllRingingTones(){
        return ringingToneService.getAllRingingTone();
    }
}
