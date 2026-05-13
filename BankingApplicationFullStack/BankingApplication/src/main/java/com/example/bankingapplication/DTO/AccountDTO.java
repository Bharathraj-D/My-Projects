package com.example.bankingapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AccountDTO {
    String holderName;
    String accountType;
    String branch;
    double balance;
    long phoneNo;
    long aadharNo;
    String address;
}
