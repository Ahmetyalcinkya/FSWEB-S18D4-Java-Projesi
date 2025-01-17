package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.exceptions.CustomerException;
import com.workintech.s18d4.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(long id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if(accountOptional.isPresent()){
            return accountOptional.get();
        }
        throw new CustomerException("The account not found.", HttpStatus.NOT_FOUND);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account deleteAccount(long id) {
        Account account = getAccount(id);
        if(account != null){
            accountRepository.delete(account);
            return account;
        }
        throw new CustomerException("The account not found.", HttpStatus.NOT_FOUND);
    }
}
