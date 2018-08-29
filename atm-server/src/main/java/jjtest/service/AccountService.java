package jjtest.service;

import jjtest.domain.Account;

public interface AccountService {


    /**
     * Returns account information for given accountId and pin code.
     * Throws AtmException if accaount or pin information is incorrect
     *
     * @param accountId
     * @param pin
     * @return
     */
    Account getAccount(String accountId, String pin);

    /**
     * Returns information about account's current balance for given accountId and pin code.
     * Throws AtmException if accaount or pin information is incorrect
     *
     * @param accountId
     * @param pin
     * @return
     */
    double getBalance(String accountId, String pin);

    /**
     * Returns maximum withdrawal amount for given accountId and pin code.
     * Throws AtmException if accaount or pin information is incorrect
     *
     * @param accountId
     * @param pin
     * @return
     */
    double getMaximumWithdrawalAmount(String accountId, String pin);



}
