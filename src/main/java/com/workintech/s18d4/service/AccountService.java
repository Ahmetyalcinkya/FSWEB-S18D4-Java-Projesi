package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();
    Account getAccount(long id);
    Account saveAccount(Account account);
    Account deleteAccount(long id);
}
