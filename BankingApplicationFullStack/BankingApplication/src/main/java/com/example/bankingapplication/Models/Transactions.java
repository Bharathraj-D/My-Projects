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
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int transactionId;
    String type;
    long senderAccNo;
    long receiverAccNo;
    double amount;
    double senderCurrentBalance;
    double receiverCurrentBalance;

    public Transactions(String type, long senderAccNo, long receiverAccNo, double amount, double senderCurrentBalance,  double receiverCurrentBalance) {
        this.type = type;
        this.senderAccNo = senderAccNo;
        this.receiverAccNo = receiverAccNo;
        this.amount = amount;
        this.senderCurrentBalance = senderCurrentBalance;
        this.receiverCurrentBalance = receiverCurrentBalance;
    }
}
