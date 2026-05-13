package com.example.bankingapplication.Repository;

import com.example.bankingapplication.Models.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    @Modifying
    @Transactional
    @Query("update Account a set a.balance=a.balance+:amount where a.accountNo=:accNo")
    void DepositAmount(@Param("amount") double amount,@Param("accNo") long accNo);
    @Modifying
    @Transactional
    @Query("update Account a set a.balance=a.balance-:amount where a.accountNo=:accNo")
    void WithdrawAmount(@Param("amount") double amount,@Param("accNo") long accNo);
    @Query("select a from Account a where a.aadharNo=:aadharNo or a.phoneNo=:phoneNo")
    Account findByDetails(@Param("phoneNo") long phoneNo,@Param("aadharNo") long aadharNo);
}
