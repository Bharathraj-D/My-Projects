package com.example.bankingapplication.Service;

import com.example.bankingapplication.DTO.TransactionDTO;
import com.example.bankingapplication.Mappers.TransactionMapper;
import com.example.bankingapplication.Models.Transactions;
import com.example.bankingapplication.Repository.TransactionsRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsServices {
     @Autowired
    TransactionsRepository tr;
    public List<Transactions> getAllTransactions() {
        return tr.findAll();
    }
    @Transactional
    public void saveTransaction(Transactions transactions) {
        tr.save(transactions);
    }
    public ResponseEntity<List<TransactionDTO>> getAllTransactionsById(long accNo) {
        List<Transactions> transactions=tr.getAllTransactionById(accNo);
        if(transactions.size()>0) {
            List<TransactionDTO> dtos = new ArrayList<>();
            for (Transactions t : transactions) {
                if (t.getSenderAccNo() == accNo) {
                    TransactionDTO dto = new TransactionDTO();
                    dto.setAccNo(t.getReceiverAccNo());
                    dto.setType("Debit");
                    dto.setAmount(t.getAmount());
                    dto.setTransactionId(t.getTransactionId());
                    dto.setCurrentBalance(t.getSenderCurrentBalance());
                    dtos.add(dto);
                } else {
                    TransactionDTO dto = new TransactionDTO();
                    dto.setAccNo(t.getSenderAccNo());
                    dto.setType("Credit");
                    dto.setAmount(t.getAmount());
                    dto.setTransactionId(t.getTransactionId());
                    dto.setCurrentBalance(t.getReceiverCurrentBalance());
                    dtos.add(dto);
                }
            }
            return new ResponseEntity<>(dtos, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.ACCEPTED);
    }
}
