package com.example.bankingapplication.Controller;

import com.example.bankingapplication.DTO.*;
import com.example.bankingapplication.Models.Account;
import com.example.bankingapplication.Service.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Accountcontroller {
    @Autowired
    AccountServices ac;
    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        return ac.getTheAccounts();
    }
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createAccount(@RequestBody AccountDTO DTO)
    {
        return ac.createAccount(DTO);
    }
    @DeleteMapping("/delete/{accountNo}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable long accountNo){
        return ac.deleteAccount(accountNo);
    }
    @PostMapping("/account")
    public ResponseEntity<AccountDTO> getAccountById(@RequestBody AccountCheck accountCheck){
        return ac.getAccount(accountCheck);
    }

    @PostMapping("/transaction")
    public ResponseEntity<HttpStatus> makeTransaction(@RequestBody TransactionDetails transaction){
        return ac.makeTransaction(transaction);
    }
    @PostMapping("/sendMoney")
    public ResponseEntity<HttpStatus> sendMoney(@RequestBody TransactionCreate transaction){
        return ac.makeATransaction(transaction);
    }

}
