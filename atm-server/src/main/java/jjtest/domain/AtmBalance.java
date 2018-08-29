package jjtest.domain;

import jjtest.service.AtmException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 *  Represents current ATM balance information including total amount and number of banknotes
 *
 */
public class AtmBalance {

    private int fullAmount;

    Map<Notes,Integer> atmNotes = new HashMap<>();

    public void initialize(Map<Notes, Integer> notes) {
        this.atmNotes = notes;
        int amount = 0;
        for (Notes note : notes.keySet() ) {
            amount += note.getNoteValue() * notes.get(note);
        }
        fullAmount = amount;
    }

    public Map<Notes,Integer> getAtmNotes() {
        return atmNotes;
    }

    public int getFullAmount() {
        return fullAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtmBalance that = (AtmBalance) o;
        return fullAmount == that.fullAmount &&
                Objects.equals(atmNotes, that.atmNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullAmount, atmNotes);
    }

    @Override
    public String toString() {
        return "AtmBalance{" +
                "fullAmount=" + fullAmount +
                ", atmNotes=" + atmNotes +
                '}';
    }
}
