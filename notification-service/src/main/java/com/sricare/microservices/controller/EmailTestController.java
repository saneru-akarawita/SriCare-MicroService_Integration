package com.sricare.microservices.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sricare.microservices.dto.NotificationDTO;
import com.sricare.microservices.service.EmailService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/notification")
@AllArgsConstructor
public class EmailTestController {

   private final EmailService emailService;

   @RequestMapping("/send-email")
   public String sendEmail(@RequestBody NotificationDTO notificationDTO){
    return emailService.sendEmail(notificationDTO);
   }
}

