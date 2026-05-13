package com.example.bankingapplication.Mappers;

import com.example.bankingapplication.DTO.TransactionDTO;
import com.example.bankingapplication.Models.Transactions;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

  TransactionDTO transactionToTransactionDTO(Transactions transaction);

}
