package com.example.bankingapplication.Mappers;

import com.example.bankingapplication.DTO.AccountDTO;
import com.example.bankingapplication.Models.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source="phoneNo",target = "phoneNo",qualifiedByName = "maskPhoneNo")
    @Mapping(source="aadharNo",target = "aadharNo",qualifiedByName = "maskAadharNo")
   AccountDTO accountToAccountDTO(Account account);
    @Named("maskPhoneNo")
    static  String maskPhoneNo(String phoneNo) {
        return "******"+phoneNo.substring(6);
    }

    @Named("maskAadharNo")
    static  String maskAadharNo(String aadharNo) {
        return "********"+aadharNo.substring(6);
    }
}
