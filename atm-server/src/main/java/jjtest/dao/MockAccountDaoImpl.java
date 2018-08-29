package jjtest.dao;


import jjtest.domain.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Mock DAO class to emulate access to the account data
 *
 */

public class MockAccountDaoImpl implements AccountDao {

    private List<Account> mockAccountStorage = new ArrayList<>();

    private final Logger logger = Logger.getLogger(MockAccountDaoImpl.class.getName());

    public MockAccountDaoImpl() {
        Account account1 = new Account();
        account1.setAccountId("123456789");
        account1.setPin("1234");
        account1.setBalance(800);
        account1.setOverdraft(200);

        Account account2 = new Account();
        account2.setAccountId("987654321");
        account2.setPin("4321");
        account2.setBalance(1230);
        account2.setOverdraft(150);

        saveAccount(account1);
        saveAccount(account2);
    }

    @Override
    public Account getAccountById(String accountId) {
        logger.warning("mockAccountStorage " + mockAccountStorage);

        Account account = mockAccountStorage.stream()
                .filter(acc -> acc!= null && acc.getAccountId().contentEquals(accountId))
                .findAny()
                .orElse(null);
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        return mockAccountStorage;
    }

    @Override
    public void saveAccount(Account account) {
        if ( ! mockAccountStorage.contains(account) ) {
            mockAccountStorage.add(account);
        }
    }

    @Override
    public void deleteAccount(Account account) {
        mockAccountStorage.remove(account);
    }
}
