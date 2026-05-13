package com.banking.bankingapplicationtransactionmeassage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class TransactionMailService
{
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String to,String subject,String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("bharathbharathraj286@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
