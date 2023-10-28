package com.sricare.Notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        javaMailSender.send(message);
    }

    // Method to send an email notification for package activation
    public void sendPackageActivationEmail(String to) {
        String subject = "Package Activation";
        String text = "Your package has been successfully activated.";
        sendEmail(to, subject, text);
    }

    //emailService.sendPackageActivationEmail(user.getEmail())

    // Method to send an email notification for successful payment
    public void sendPaymentConfirmationEmail(String to) {
        String subject = "Payment Confirmation";
        String text = "Your payment has been successfully processed.";
        sendEmail(to, subject, text);
    }

    //emailService.sendPaymentConfirmationEmail(user.getEmail())

    // Method to send an email notification for disconnection
    public void sendDisconnectionEmail(String to) {
        String subject = "Connection Disconnection";
        String text = "Your connection has been disconnected.";
        sendEmail(to, subject, text);
    }

    //emailService.sendDisconnectionEmail(user.getEmail())
}
