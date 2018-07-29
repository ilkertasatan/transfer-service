package com.ingenico.epayments.transferservice.controller;

import com.ingenico.epayments.transferservice.domain.Account;
import com.ingenico.epayments.transferservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Account.class)
@RequestMapping(value = "/accounts", produces = "application/json")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity create(@RequestBody Account account) throws Exception {
        accountService.createAccount(account);
        return new ResponseEntity(account.getId(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, consumes = "application/json")
    public List<Account> getAll() {
        List<Account> accounts = accountService.getAllAccounts();
        return accounts;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = "application/json")
    public Account get(@PathVariable Long id) throws Exception {
        Account account = accountService.getAccount(id);
        return account;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}

