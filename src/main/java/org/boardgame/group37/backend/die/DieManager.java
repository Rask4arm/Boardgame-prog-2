package org.boardgame.group37.backend.die;
import java.util.ArrayList;

public class DieManager {

    // Properties
    private ArrayList<Die> dice = new ArrayList<>();

    // Constructor
    public DieManager() {
        System.out.println("\nDebug: DieManager created.");
    }

    // Methods
    public void dieAdd() {
        dice.add(new Die());
        System.out.println(String.format("Debug: Die added. Current number of dice: %d", dice.size()));
    }

    public void dieRemove() {
        dice.remove(dice.size()-1);
        System.out.println(String.format("Debug: Die removed. Current number of dice: %d", dice.size()));
    }

    public void diceThrow() {
        System.out.println(String.format("Debug: Dice thrown, with number of dice: %d", diceCount()));
        for(Die d : dice) {
            d.executeThrow();
        }
        System.out.println(String.format("Debug: Total value of dice: %d", diceValue()));
    }

    public int diceValue() {
        return dice.stream().mapToInt((x) -> x.getValue()).sum();
    }

    public int diceCount() {
        return dice.size();
    }
}
