package com.example.bankingapplication.Controller;

import com.example.bankingapplication.DTO.TransactionCreate;
import com.example.bankingapplication.DTO.TransactionDTO;
import com.example.bankingapplication.Models.Transactions;
import com.example.bankingapplication.Service.AccountServices;
import com.example.bankingapplication.Service.TransactionsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionsServices ts;
    @Autowired
    AccountServices as;
    @PostMapping("/MakeTransaction")
    public ResponseEntity<HttpStatus> makeTransaction(@RequestBody TransactionCreate trans){
        return as.makeATransaction(trans);
    }
    @GetMapping("/Transactions")
    public List<Transactions> getTransactions(){
        return ts.getAllTransactions();
    }
    @GetMapping("/Transactions/{id}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsById(@PathVariable long id){
        return ts.getAllTransactionsById(id);
    }
}
