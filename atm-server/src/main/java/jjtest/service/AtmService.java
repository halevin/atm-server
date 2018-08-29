package jjtest.service;

import jjtest.domain.AtmBalance;
import jjtest.domain.AtmResponse;
import jjtest.domain.Notes;

import java.util.Map;

public interface AtmService {

    /**
     * Withdraws given amount from ATM
     * Throws AtmException in the case of incorrect accountId, pin or
     * absense of money in the current account or ATM
     *
     * @param account
     * @param pin
     * @param amount
     * @return
     */
    AtmResponse withdrawal(String account, String pin, int amount);

    /**
     * Service method to test if there are enough notes in the ATM
     *
     * @param notes
     * @return
     */
    boolean testNotes(Map<Notes, Integer> notes);


    /**
     * Extract notes from the current ATM balance
     *
     *
     * @param notes
     */
    void extractNotes(Map<Notes, Integer> notes);

}
