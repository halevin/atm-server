package jjtest.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Wrapper class for REST interface responses
 * It was created as common as possible and includes
 * flag if operation wassuccessfull or not,
 * Map object with information about notes
 * Total value of operation/information
 * Informative message
 */

public class AtmResponse {

    private boolean successfull;

    private Map<Notes, Integer> notes = new HashMap<>();

    private double value;

    private String message;

    public AtmResponse(){}

    public AtmResponse(boolean successfull, String message) {
        this.successfull = successfull;
        this.message = message;
    }

    public boolean isSuccessfull() {
        return successfull;
    }

    public void setSuccessfull(boolean successfull) {
        this.successfull = successfull;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<Notes, Integer> getNotes() {
        return notes;
    }

    public void setNotes(Map<Notes, Integer> notes) {
        this.notes = notes;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtmResponse response = (AtmResponse) o;
        return successfull == response.successfull &&
                Double.compare(response.value, value) == 0 &&
                Objects.equals(notes, response.notes) &&
                Objects.equals(message, response.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(successfull, notes, value, message);
    }

    @Override
    public String toString() {
        return "AtmResponse{" +
                "successfull=" + successfull +
                ", notes=" + notes +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
