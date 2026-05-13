package com.banking.bankingapplicationtransactionmeassage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMessageRepository extends JpaRepository<TransactionMessage,Long> {
}
