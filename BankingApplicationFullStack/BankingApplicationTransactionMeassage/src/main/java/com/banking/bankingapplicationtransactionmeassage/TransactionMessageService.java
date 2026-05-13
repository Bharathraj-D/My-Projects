package com.banking.bankingapplicationtransactionmeassage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransactionMessageService {
    @Autowired
    TransactionMessageRepository transactionMessageRepository;

    @Autowired
    TransactionMailService transactionMailService;



    @KafkaListener(topics = "transaction",groupId = "transaction-group")
    public void saveTransactionMessages(TransactionMessageDTO transactionMessageDTO){
      String message="";
      String accNo=transactionMessageDTO.getAccountNo();
      String type=transactionMessageDTO.getType();
      double amount=transactionMessageDTO.getAmount();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String date = transactionMessageDTO.getDate().format(formatter);
      if(type.equalsIgnoreCase("debit")){
          message="RS:"+amount +" Debited from your Account Number "+accNo+" on"+date+".";
      }
      else{
          message="RS:"+amount +" Credited to your Account Number "+accNo+" on"+date+".";
      }
      System.out.println(message);
      TransactionMessage message1=new TransactionMessage(0,message);
      transactionMessageRepository.save(message1);
      transactionMailService.sendMail(transactionMessageDTO.getEmail(),"Trust Only Bank -Transaction",message);
    }

    public List<TransactionMessage> getAllMessages() {
        return transactionMessageRepository.findAll();
    }
}
