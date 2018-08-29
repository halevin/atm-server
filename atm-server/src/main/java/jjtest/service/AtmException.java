package jjtest.service;


/**
 * Exception class to represent possible problems during operation with ATM and accounts
 *
 */
public class AtmException extends RuntimeException {

    public AtmException() {
        super();
    }

    public AtmException(String message) {
        super(message);
    }

}
