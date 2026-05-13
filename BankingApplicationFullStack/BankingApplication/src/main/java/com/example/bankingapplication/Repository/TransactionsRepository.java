package com.example.bankingapplication.Repository;

import com.example.bankingapplication.Models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Long> {
    @Query("Select trans from Transactions trans where trans.senderAccNo=:accNo or trans.receiverAccNo=:accNo")
    List<Transactions> getAllTransactionById(@Param("accNo") long accNo);
}
