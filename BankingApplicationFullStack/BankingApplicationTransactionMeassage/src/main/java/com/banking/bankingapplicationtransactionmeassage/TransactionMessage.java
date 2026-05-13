package com.banking.bankingapplicationtransactionmeassage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransactionMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionMessageId;
    private String TransactionMessage;

    public TransactionMessage(String message) {
    }
}
