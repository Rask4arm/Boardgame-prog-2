package org.boardgame.group37.backend;

public class Die {

    // Properties
    private int value = 1;
    
    // Contstructors
    public Die() {
    }
    public Die(int value){
        this.value = value;
    }

    // Methods
    public int getValue() {
        return value;
    }
    public void executeThrow() {
        value = (int) (Math.random() * 6) + 1;
        System.out.println(String.format("Debug: Die thrown. Value: %d", value));
    }
}
