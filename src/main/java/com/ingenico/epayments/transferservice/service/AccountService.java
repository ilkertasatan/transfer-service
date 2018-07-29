package com.ingenico.epayments.transferservice.service;

import com.ingenico.epayments.transferservice.domain.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account) throws Exception;
    Account getAccount(Long id) throws Exception;
    List<Account> getAllAccounts();
}
