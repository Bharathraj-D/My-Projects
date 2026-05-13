package com.example.bankingapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TransactionDetails {
    private String type;
    private long AccNo;
    private double Amount;
}
