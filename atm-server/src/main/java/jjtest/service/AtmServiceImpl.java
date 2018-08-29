package jjtest.service;

import jjtest.dao.AtmDao;
import jjtest.domain.AtmBalance;
import jjtest.domain.AtmResponse;
import jjtest.domain.Notes;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class AtmServiceImpl implements AtmService {

    @Autowired
    public AccountService accountService;

    @Autowired
    public AtmDao atmDao;

    @Override
    public AtmResponse withdrawal(String accountId, String pin, int amount) {
        AtmResponse response = null;
        AtmBalance atmBalance = atmDao.getAtmBalance();
        if ( atmBalance != null && checkAmount( accountId, pin, amount) ){

            int n50 = Math.min( amount / 50, atmBalance.getAtmNotes().get(Notes.NOTE50) );
            int rest = amount - n50 * 50;
            int n20 =  Math.min( rest / 20, ( atmBalance.getAtmNotes().get(Notes.NOTE20) ) );
            rest -= n20 * 20;
            int n10 =  Math.min( rest / 10, ( atmBalance.getAtmNotes().get(Notes.NOTE10) ) );
            rest -= n10 * 10;
            int n5 =  rest / 5;
            if ( n5 > atmBalance.getAtmNotes().get(Notes.NOTE5)) {
                throw new AtmException("There are not enough notes for amount requested");
            } else {
                Map<Notes, Integer> notes = new HashMap<>();
                notes.put(Notes.NOTE50, n50);
                notes.put(Notes.NOTE20, n20);
                notes.put(Notes.NOTE10, n10);
                notes.put(Notes.NOTE5, n5);
                response = new AtmResponse(true, "Take your money");
                response.setNotes(notes);
                response.setValue(amount);

                extractNotes(notes);
            }
        }

        return response;
    }

    boolean checkAmount(String accountId, String pin, int amount) {
        if ( accountService.getMaximumWithdrawalAmount(accountId, pin ) < amount ) {
            throw new AtmException("The amount requested exceeds your maximum available amount");
        }
        if ( amount > atmDao.getAtmBalance().getFullAmount() ) {
            throw new AtmException("Not enough money in ATM");
        }
        if ( amount % 5 != 0 || amount == 0) {
            throw new AtmException("Incorrect amount");
        }

        return true;
    }

    @Override
    public boolean testNotes(Map<Notes, Integer> notes) {
        AtmBalance atmBalance = atmDao.getAtmBalance();
        for ( Notes note : notes.keySet() ) {
            if ( ! atmBalance.getAtmNotes().containsKey(note) || atmBalance.getAtmNotes().get(note) < notes.get(note) ) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void extractNotes(Map<Notes, Integer> notes) {
        AtmBalance atmBalance = atmDao.getAtmBalance();
        boolean testNotes = testNotes(notes);
        if ( ! testNotes ) {
            throw new AtmException(" ATM does not have enough notes ");
        } else {
            for ( Notes note : notes.keySet() ) {
                Integer value = atmBalance.getAtmNotes().get(note) - notes.get(note);
                atmBalance.getAtmNotes().put(note, value);
            }
        }
    }

}
