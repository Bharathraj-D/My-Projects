package com.banking.bankapplicationloginmessagehandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class LoginMailSender
{
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to,String subject,String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("bharathbharathraj286@gmail.com");
        message.setTo(to);
        message.setSubject("Login Notification");
        message.setText(body);
        mailSender.send(message);
    }
}
