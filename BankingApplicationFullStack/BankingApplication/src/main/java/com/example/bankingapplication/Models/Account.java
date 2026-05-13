package com.example.bankingapplication.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Component
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long accountNo;

    String holderName;
    String accountType;

    String branch;
    double balance;
    long phoneNo;
    long aadharNo;
    String address;
    String password;
    public Account(String holderName, String accountType, String branch, double balance, long phoneNo, long aadharNo, String address,String password) {
        this.holderName = holderName;
        this.accountType = accountType;
        this.branch = branch;
        this.balance = balance;
        this.phoneNo = phoneNo;
        this.aadharNo = aadharNo;
        this.address = address;
        this.password = password;
    }

}
