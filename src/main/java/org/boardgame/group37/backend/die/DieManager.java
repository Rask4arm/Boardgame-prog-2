package org.boardgame.group37.backend.die;
import java.util.ArrayList;

/*
 * DieManager class is responsible for managing dice data.
 * It stores an ArrayList of Die objects.
 */
public class DieManager {

    // Properties
    private ArrayList<Die> dice = new ArrayList<>();

    /*
     * Constructor
     * Initializes the DieManager with an empty ArrayList of Die objects.
     */
    public DieManager() {
        System.out.println("\nDebug: DieManager created.");
    }

    // Methods

    /*
     * dieAdd method adds a new Die object to the ArrayList.
     */
    public void dieAdd() {
        dice.add(new Die());
        System.out.println(String.format("Debug: Die added. Current number of dice: %d", dice.size()));
    }

    /*
     * dieRemove method removes the last Die object from the ArrayList.
     */
    public void dieRemove() {
        dice.remove(dice.size()-1);
        System.out.println(String.format("Debug: Die removed. Current number of dice: %d", dice.size()));
    }

    /*
     * diceThrow method throws all dice in the ArrayList.
     */
    public void diceThrow() {
        System.out.println(String.format("Debug: Dice thrown, with number of dice: %d", diceCount()));
        for(Die d : dice) {
            d.executeThrow();
        }
        System.out.println(String.format("Debug: Total value of dice: %d", diceValue()));
    }

    /*
     * diceValue method returns the total value of all dice in the ArrayList.
     * @return total value of all dice in the ArrayList
     */
    public int diceValue() {
        return dice.stream().mapToInt((die) -> die.getValue()).sum();
    }

    /*
     * diceCount method returns the number of dice in the ArrayList.
     * @return number of dice in the ArrayList
     */
    public int diceCount() {
        return dice.size();
    }

    /*
     * toString method returns the name of the DieManager and the number of dice and total value.
     * @return name of the DieManager and the number of dice and total value
     */
    @Override
    public String toString() {
        return String.format("DieManager | %d Dice | %d Value", diceCount(), diceValue());
    }
}
