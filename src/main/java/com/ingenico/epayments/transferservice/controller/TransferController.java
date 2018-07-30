package com.ingenico.epayments.transferservice.controller;

import com.ingenico.epayments.transferservice.domain.Account;
import com.ingenico.epayments.transferservice.domain.Transfer;
import com.ingenico.epayments.transferservice.service.TransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "/transfers", description = "Operations about transfers")
@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Account.class)
@RequestMapping(value = "/transfers", produces = "application/json")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @ApiOperation(value = "Makes a transfer", notes = "Makes a transfer from source account to target account", response = Transfer.class)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity make(@RequestBody Transfer transfer) throws Exception {
        transferService.makeTransfer(transfer);
        return new ResponseEntity(transfer.getId(), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Finds a transfer by ID", response = Transfer.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Transfer get(@PathVariable Long id) throws Exception {
        Transfer transfer = transferService.getTransfer(id);
        return transfer;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
