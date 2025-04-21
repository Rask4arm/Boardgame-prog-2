package org.boardgame.group37.backend.die;

/**
 * Die class is responsible for managing the die.
 * It stores the value and can throw the die.
 */
public class Die {

    // Properties
    private int value = 1;
    
    /**
     * Constructor
     * Initializes the Die with a value of 1.
     */
    public Die() {
    }

    /**
     * Constructor
     * Initializes the Die with the specified value.
     * @param value: Die value
     */
    public Die(int value) {
        this.value = value;
    }

    // Methods

    /**
     * getValue method returns the value of the die.
     * @return value of the die
     */
    public int getValue() {
        return value;
    }

    /**
     * executeThrow method throws the die.
     * The value is set to a random number int between 1 and 6.
     */
    public void executeThrow() {
        value = (int) (Math.random() * 6) + 1;
        System.out.println(String.format("Debug: Die thrown. Value: %d", value));
    }

    /**
     * toString method returns the value of the die as a string.
     * @return value of the die as a string
     */
    @Override
    public String toString() {
        return String.format("Die | Value: %d", value);
    }
}
