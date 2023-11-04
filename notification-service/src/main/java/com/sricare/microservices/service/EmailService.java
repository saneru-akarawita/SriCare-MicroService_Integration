package com.sricare.microservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sricare.microservices.dto.NotificationDTO;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public String sendEmail(NotificationDTO notificationDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notificationDTO.getTo());
        message.setSubject(notificationDTO.getSubject());
        message.setText(notificationDTO.getText());

        try{
            javaMailSender.send(message);
            return "Email sent successfully.";
        } catch(MailException e){
            return e.getMessage();
        }
        
    }
}
