package com.example.bankingapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TransactionDTO {
    int transactionId;
    String type;
    long AccNo;
    double amount;
    double currentBalance;
}
