package org.boardgame.group37.backend;
import java.util.ArrayList;

public class DieManager {

    // Properties
    private ArrayList<Die> dice = new ArrayList<>();

    // Constructor
    public DieManager() {
        
    }

    // Methods
    public void dieAdd() {
        dice.add(new Die());
    }

    public void dieRemove() {
        dice.remove(dice.size()-1);
    }

    public void diceThrow() {
        for(Die d : dice) {
            d.executeThrow();
        }
    }

    public int diceValue() {
        return dice.stream().mapToInt((x) -> x.getValue()).sum();
    }
}
