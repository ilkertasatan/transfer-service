package com.ingenico.epayments.transferservice.service;

import com.ingenico.epayments.transferservice.domain.Account;
import com.ingenico.epayments.transferservice.domain.Transfer;
import com.ingenico.epayments.transferservice.repository.TransferRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service("transferService")
public class TransferServiceImpl implements TransferService {

    @Autowired
    private AccountService accountService;

    @Autowired
    TransferRepository repository;

    @Override
    public void makeTransfer(Transfer transfer) throws Exception {

        Account sourceAccount = accountService.getAccount(transfer.getSourceAccountId());
        if (sourceAccount == null) {
            throw new NotFoundException("");
        }

        if (sourceAccount.getId() == transfer.getTargetAccountId()) {
            throw new RuntimeException("Source and target cannot be same");
        }

        Account targetAccount = accountService.getAccount(transfer.getTargetAccountId());
        if (targetAccount == null) {
            throw new NotFoundException("");
        }

        sourceAccount.withDraw(transfer.getAmount());

        try {
            targetAccount.deposit(transfer.getAmount());
        }
        catch (Exception ex) {
            sourceAccount.deposit(transfer.getAmount());
            throw ex;
        }

        transfer.setDate(new Date());
        repository.create(transfer);
    }

    @Override
    public Transfer getTransfer(Long id) throws Exception {
        Optional<Transfer> transfer = repository.findById(id);
        if (transfer.isPresent()) {
            return transfer.get();
        }

        throw new NotFoundException("Given transfer was not found in the database.");
    }

}
