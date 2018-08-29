package jjtest.dao;

import jjtest.domain.Account;

import java.util.List;

/*
 *  In the real life I would use CrudRepository interface, but for simplicity I am using mock DAO class.
 *
 */
public interface AccountDao {

    Account getAccountById(String accountId);

    List<Account> getAllAccounts();

    void saveAccount(Account account);

    void deleteAccount(Account account);

}
