package com.banking.bankingapplicationtransactionmeassage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TransactionMessageDTO {
    private String accountNo;
    String email;
    double amount;
    String type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime date;
}

