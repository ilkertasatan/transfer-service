package com.ingenico.epayments.transferservice;

import com.ingenico.epayments.transferservice.domain.Account;
import com.ingenico.epayments.transferservice.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class AccountTests {

    private AccountRepository repository = new AccountRepository();

    @Test
    public void create_new_account() {

        Account account = new Account();
        account.setName("Name");
        account.setBalance(150.0d);

        if (account != null) {
            assertThat(repository.create(account), instanceOf(Account.class));
            assertNotNull("Name isn't null",account.getName());
            assertNotNull("Balance isn't null", account.getBalance());
        }

       assertNotNull("Account isn't null", account);
    }

    @Test
    public void when_find_all_by_id(){

        Account account = new Account();
        account.setName("Name");
        account.setBalance(150.0d);

        repository.create(account);

        Optional<Account> testAccount = repository.findById(account.getId());

        assertThat(testAccount.get().getName(), is(account.getName()));
    }

    @Test
    public void when_find_all(){

        Account account1 = new Account();
        account1.setName("Name");
        account1.setBalance(150.0d);

        repository.create(account1);

        Account account2 = new Account();
        account2.setName("Name");
        account2.setBalance(150.0d);

        repository.create(account2);

        List<Account> accounts = repository.findAll();

        assertThat(accounts.size(), is(2));
        assertThat(accounts.get(0), is(account1));
        assertThat(accounts.get(1), is(account2));
    }

}
