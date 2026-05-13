package com.banking.bankapplicationloginmessagehandler;

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
public class LoginMessageDTO {
    String accountNo;
    String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime date;
}
