package com.banking.bankapplicationloginmessagehandler;

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
public class LoginMessage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long messageId;

    private String message;

    public LoginMessage(String message) {
    }
}
