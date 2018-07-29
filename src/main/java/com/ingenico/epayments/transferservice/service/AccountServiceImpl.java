package com.ingenico.epayments.transferservice.service;

import com.ingenico.epayments.transferservice.domain.Account;
import com.ingenico.epayments.transferservice.repository.AccountRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("accountService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public Account createAccount(Account account) throws Exception {

        if (account == null) {
            throw new IllegalArgumentException("The account object cannot be null.");
        }

        if (account.getName() == null || account.getName().isEmpty()) {
            throw new IllegalArgumentException("The name field cannot be null or empty");
        }

        try {
            Account createdAccount = repository.create(account);
            return createdAccount;
        }
        catch (Exception ex) {
            throw new Exception("An exception was handled.");
        }
    }

    @Override
    public Account getAccount(Long id) throws Exception {

        Optional<Account> account = repository.findById(id);
        if (account.isPresent()) {
            return account.get();
        }

        throw new NotFoundException("Given account was not found in the database.");
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = repository.findAll();
        return accounts;
    }
}
