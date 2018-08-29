package jjtest.domain;

import java.io.Serializable;
import java.util.Objects;


/**
 * Class to represent account related information
 *
 */
public class Account implements Serializable {


    private static final long serialVersionUID = -4490998082958157550L;

    private String accountId;
    private String pin;
    private double balance;
    private double overdraft;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String id) {
        this.accountId = id;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 &&
                Double.compare(account.overdraft, overdraft) == 0 &&
                Objects.equals(accountId, account.accountId) &&
                Objects.equals(pin, account.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, pin, balance, overdraft);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", pin='" + pin + '\'' +
                ", balance=" + balance +
                ", overdraft=" + overdraft +
                '}';
    }
}
