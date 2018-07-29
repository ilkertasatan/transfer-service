package com.ingenico.epayments.transferservice;

import com.ingenico.epayments.transferservice.domain.Account;
import com.ingenico.epayments.transferservice.domain.Transfer;
import com.ingenico.epayments.transferservice.repository.TransferRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TransferTests {

    private TransferRepository repository = new TransferRepository();
    private Account sourceAccount;
    private Account targetAccount;

    public TransferTests(){

        sourceAccount = new Account();
        sourceAccount.setId((long) 1);
        sourceAccount.setName("Name1");
        sourceAccount.setBalance(150);

        targetAccount = new Account();
        targetAccount.setId((long) 2);
        targetAccount.setName("Name2");
        targetAccount.setBalance(50);

    }

    @Test
    public void create_new_transfer() {

        Transfer transfer = new Transfer();
        transfer.setSourceAccountId(sourceAccount.getId());
        transfer.setTargetAccountId(targetAccount.getId());
        transfer.setAmount(25);

        if (transfer != null) {
            assertThat(repository.create(transfer), instanceOf(Transfer.class));
            assertTrue(transfer.getSourceAccountId() == 1);
            assertTrue(transfer.getTargetAccountId() == 2);
        }

        assertNotNull("Transfer isn't null", transfer);
    }

    @Test
    public void make_transfer_from_source_to_target() throws Exception {

        Transfer transfer = new Transfer();
        transfer.setSourceAccountId(sourceAccount.getId());
        transfer.setTargetAccountId(targetAccount.getId());
        transfer.setAmount(25);

        makeTransfer(25);

        assertTrue(sourceAccount.getBalance() == 125);
        assertTrue(targetAccount.getBalance() == 75);
    }

    @Test
    public void  make_transfer_overdrawn() {
        Transfer transfer = new Transfer();
        transfer.setSourceAccountId(sourceAccount.getId());
        transfer.setTargetAccountId(targetAccount.getId());
        transfer.setAmount(250);

        try{
            makeTransfer(250);
        }
        catch (Exception ex){
        }

        assertTrue(sourceAccount.getBalance() == 150);
        assertTrue(targetAccount.getBalance() == 50);
    }

    @Test
    public void make_transfer_test_concurrency()  {

        Transfer transfer = new Transfer();
        transfer.setSourceAccountId(sourceAccount.getId());
        transfer.setTargetAccountId(targetAccount.getId());
        transfer.setAmount(10);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {

                try {
                    makeTransfer(10);
                }
                catch (Exception ex) {
                }

            });

            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                return;
            }
        }

        assertTrue(sourceAccount.getBalance() == 50);
        assertTrue(targetAccount.getBalance() == 150);
    }

    private void makeTransfer(double amount) throws Exception {
        sourceAccount.withDraw(amount);
        targetAccount.deposit(amount);
    }
}
