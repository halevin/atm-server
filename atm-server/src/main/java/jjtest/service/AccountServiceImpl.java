package jjtest.service;

import jjtest.dao.AccountDao;
import jjtest.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImpl implements AccountService {

    @Autowired
    public AccountDao accountDao;

    @Override
    public Account getAccount(String accountId, String pin) {
        Account account = accountDao.getAccountById(accountId);
        if ( account != null && pin != null && pin.equals(account.getPin())) {
            return account;
        }
        return null;
    }

    @Override
    public double getBalance(String accountId, String pin) {
        Account account = getAccount(accountId, pin);
        if ( account != null ) {
            return account.getBalance();
        } else {
            throw new AtmException("Account is not authorized");
        }
    }

    @Override
    public double getMaximumWithdrawalAmount(String accountId, String pin) {
        Account account = getAccount(accountId, pin);
        if ( account != null ) {
            return account.getBalance() + account.getOverdraft();
        } else {
            throw new AtmException("Account is not authorized");
        }
    }

}
