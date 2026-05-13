package com.example.bankingapplication.Service;
import com.example.bankingapplication.DTO.AccountCheck;
import com.example.bankingapplication.DTO.AccountDTO;
import com.example.bankingapplication.DTO.TransactionCreate;
import com.example.bankingapplication.DTO.TransactionDetails;
import com.example.bankingapplication.Mappers.AccountMapper;
import com.example.bankingapplication.Models.Account;
import com.example.bankingapplication.Models.Transactions;
import com.example.bankingapplication.Repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServices {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    AccountRepository ar;
    @Autowired
    TransactionsServices tr;
    @Transactional
    public ResponseEntity<HttpStatus> createAccount(AccountDTO DTO) {
        Account acc=ar.findByDetails(DTO.getPhoneNo(),DTO.getAadharNo());
        if(acc==null) {
        String password=passwordEncoder.encode(DTO.getHolderName().substring(0,4).trim()+String.valueOf(DTO.getAadharNo()).substring(0,4));
            Account account = new Account(DTO.getHolderName(), DTO.getAccountType(), DTO.getBranch(), DTO.getBalance(), DTO.getPhoneNo(), DTO.getAadharNo(), DTO.getAddress(),password);
            ar.save(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public List<Account> getTheAccounts() {
        return ar.findAll();
    }
    @Transactional
    public ResponseEntity<HttpStatus> deleteAccount(long accountNo) {
        ar.deleteById(accountNo);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public ResponseEntity<AccountDTO> getAccount(AccountCheck accountCheck) {
        Account account= ar.findById(accountCheck.getAccountNo()).orElse(null);
        if(account==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean check=passwordEncoder.matches(accountCheck.getPassword(),account.getPassword());
        if(check) {
            AccountMapper accountMapper= Mappers.getMapper(AccountMapper.class);
            AccountDTO DTO=accountMapper.accountToAccountDTO(account);
            return new ResponseEntity<>(DTO, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @Transactional
    public ResponseEntity<HttpStatus> makeTransaction(TransactionDetails transaction) {
        if (transaction.getAmount()<= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
      long accountNo=transaction.getAccNo();
      String type=transaction.getType();
      double amount=transaction.getAmount();
      Account account=ar.findById(accountNo).orElse(null);
      if(account==null) {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      else{
      if(type.equalsIgnoreCase("Withdrawal")) {
          if(account.getBalance()>=amount) {
          account.setBalance(account.getBalance()-amount);
          double balance=account.getBalance();
          Transactions transactions=new Transactions(type,accountNo,0,amount,balance,0);
          ar.save(account);
          tr.saveTransaction(transactions);
          }
          else{
              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
          }
      }else if(type.equalsIgnoreCase("Deposit")) {
          account.setBalance(account.getBalance()+amount);
          double balance=account.getBalance();
          Transactions transactions=new Transactions(type,0,accountNo,amount,0,balance);
          ar.save(account);
          tr.saveTransaction(transactions);
      }
      else{
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<>(HttpStatus.OK);
    }}
    @Transactional
    public ResponseEntity<HttpStatus> makeATransaction(TransactionCreate trans) {
        if (trans.getAmount() <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (trans.getSenderId()==trans.getReceiverId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Account sender=ar.findById(trans.getSenderId()).orElse(null);
        Account receiver=ar.findById(trans.getReceiverId()).orElse(null);
        if(sender==null||receiver==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else if(sender.getBalance()<trans.getAmount()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sender.setBalance(sender.getBalance()-trans.getAmount());
        receiver.setBalance(receiver.getBalance()+trans.getAmount());
        ar.save(sender);
        ar.save(receiver);
       Transactions transactions = new Transactions("Online Transaction", trans.getSenderId(),trans.getReceiverId(), trans.getAmount(),sender.getBalance(),receiver.getBalance());
       tr.saveTransaction(transactions);
       return new ResponseEntity<>(HttpStatus.OK);
    }
}

