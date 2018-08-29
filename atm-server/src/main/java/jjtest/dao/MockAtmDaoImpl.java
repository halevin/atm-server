package jjtest.dao;

import jjtest.domain.AtmBalance;
import jjtest.domain.Notes;

import java.util.HashMap;
import java.util.Map;

/**
 * Mock DAO class to emulate access to the ATM data
 *
 */
public class MockAtmDaoImpl implements AtmDao {

    private AtmBalance atmBalance = new AtmBalance();

    public MockAtmDaoImpl() {
        AtmBalance atmBalance = new AtmBalance();
        Map<Notes, Integer> notes = new HashMap<>();
        notes.put(Notes.NOTE50, 20);
        notes.put(Notes.NOTE20, 30);
        notes.put(Notes.NOTE10, 30);
        notes.put(Notes.NOTE5, 20);
        atmBalance.initialize(notes);

        setAtmBalance(atmBalance);

    }

    @Override
    public AtmBalance getAtmBalance() {
        return atmBalance;
    }

    @Override
    public void setAtmBalance(AtmBalance balance) {
        this.atmBalance = balance;
    }
}
