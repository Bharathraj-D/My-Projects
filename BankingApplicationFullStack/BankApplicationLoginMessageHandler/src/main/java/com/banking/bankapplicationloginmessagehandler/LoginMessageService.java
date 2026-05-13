package com.banking.bankapplicationloginmessagehandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LoginMessageService {
     @Autowired
     LoginMessageRepository loginMessageRepository;

     @Autowired
     LoginMailSender loginMailSender;

    @KafkaListener(topics = "login",groupId = "bharath-group")
    public void getLoginMessage(LoginMessageDTO loginMessageDTO){
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

           String formatted = loginMessageDTO.getDate().format(formatter);

           String message="ACCOUNT NO: "+loginMessageDTO.getAccountNo()+"\nLogin Successfull!!!!"+"\nTime:"+formatted+"\nIf not done by you change assword and report";
           System.out.print(message);
           LoginMessage loginMessage=new LoginMessage(0,message);
           loginMessageRepository.save(loginMessage);
           loginMailSender.sendMail(loginMessageDTO.getEmail(),"Trust Only Bank - Login Alert",message);
    }

    public List<LoginMessage> getAllMessages() {
       return loginMessageRepository.findAll();
    }
}
