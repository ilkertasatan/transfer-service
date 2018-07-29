package com.ingenico.epayments.transferservice.service;

import com.ingenico.epayments.transferservice.domain.Transfer;

public interface TransferService {
    void makeTransfer(Transfer transfer) throws Exception;
    Transfer getTransfer(Long id) throws Exception;
}
