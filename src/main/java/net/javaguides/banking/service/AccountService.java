package net.javaguides.banking.service;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id,double amount);

    AccountDto withdraw(Long id,double amount);
}


