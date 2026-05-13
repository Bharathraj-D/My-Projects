package com.example.bankingapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TransactionCreate {
    long senderId;
    long receiverId;
    double amount;
}
