package com.banking.bankapplicationloginmessagehandler;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginMessageRepository extends JpaRepository<LoginMessage,Long> {
}
