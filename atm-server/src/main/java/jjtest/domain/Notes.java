package jjtest.domain;


/**
 * Enum class to represent €5, €10, €20 and €50 notes
 *
 */
public enum Notes {

    NOTE50 ( 50 ),
    NOTE20 ( 20 ),
    NOTE10 ( 10 ),
    NOTE5  ( 5 );

    private final int noteValue;

    Notes(int value) {
        this.noteValue = value;
    }

    public int getNoteValue() {
        return noteValue;
    }
}
