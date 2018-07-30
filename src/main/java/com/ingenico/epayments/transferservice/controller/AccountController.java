package com.ingenico.epayments.transferservice.controller;

import com.ingenico.epayments.transferservice.domain.Account;
import com.ingenico.epayments.transferservice.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(value = "/accounts", description = "Operations about accounts")
@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Account.class)
@RequestMapping(value = "/accounts", produces = "application/json")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @ApiOperation(value = "Creates a account", response = Account.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Account account) throws Exception {
        accountService.createAccount(account);
        return new ResponseEntity(account.getId(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Lists all accounts", response = Account.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET)
    public List<Account> getAll() {
        List<Account> accounts = accountService.getAllAccounts();
        return accounts;
    }

    @ApiOperation(value = "Finds a account by ID", response = Account.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account get(@PathVariable Long id) throws Exception {
        Account account = accountService.getAccount(id);
        return account;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}

