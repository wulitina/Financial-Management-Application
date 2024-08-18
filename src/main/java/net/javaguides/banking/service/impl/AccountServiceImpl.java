package net.javaguides.banking.service.impl;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.dto.TransferFundDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.exception.AccountException;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account =  accountRepository.
               findById(id).
               orElseThrow(()->new AccountException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =  accountRepository.
                findById(id).
                orElseThrow(()->new AccountException("Account does not exist"));
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        Account savedAccount =  accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account =  accountRepository.
                findById(id).
                orElseThrow(()->new AccountException("Account does not exist"));
        if (account.getBalance() < amount){
            throw new AccountException("Insufficient amount");
        }

        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
        Account savedAccount =  accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts= accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(long id) {
        Account account =  accountRepository.
                findById(id).
                orElseThrow(()->new AccountException("Account does not exist"));
        accountRepository.deleteById(account.getId());
    }

    @Override
    public void transferFunds(TransferFundDto transferFundDto) {
        // Retrieve the account from which we send te amount
        Account fromAccount = accountRepository.findById(transferFundDto.fromAccountId())
                .orElseThrow(()->new AccountException("From Account does not exist"));
        // Retrieve the account to which we sent the amount
        Account toAccount =   accountRepository.findById(transferFundDto.toAccountId())
                .orElseThrow(()->new AccountException("To Account does not exist"));

        // debit the amount from fromAccount object
        fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.amount());

        // credit the amount to toAccount Object
        toAccount.setBalance(toAccount.getBalance()+ transferFundDto.amount());
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
