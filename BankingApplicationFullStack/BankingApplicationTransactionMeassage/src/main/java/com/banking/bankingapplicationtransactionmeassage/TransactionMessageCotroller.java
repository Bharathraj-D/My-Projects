package com.banking.bankingapplicationtransactionmeassage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class TransactionMessageCotroller {
    @Autowired
    TransactionMessageService transactionMessageService;
    @GetMapping("/get/transactionmessages")
    public List<TransactionMessage> getAllTransactionMessages(){
        return transactionMessageService.getAllMessages();
    }
}
