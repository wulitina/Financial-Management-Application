package net.javaguides.banking.service;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
}


