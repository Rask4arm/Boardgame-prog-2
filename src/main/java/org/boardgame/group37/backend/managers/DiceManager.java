package org.boardgame.group37.backend.managers;
import java.util.ArrayList;
import org.boardgame.group37.backend.objects.Dice;

public class DiceManager {

  // Dice list
  private ArrayList<Dice> dices = new ArrayList<Dice>();

  // Constructor
  public DiceManager() {
  }

  // Methods
  // Add dice to dice list
  public void diceAdd() {
    dices.add(new Dice());
  }

  // Remove last dice from dice list
  public void diceRemove() {
    if (!dices.isEmpty()) {
      dices.remove(dices.size() - 1);
    }
  }

  // Roll all dices
  public void diceRollAll() {
    for (int i = 0; i < dices.size(); i++) {
      dices.get(i).roll();
    }
  }

  // Get dice sum of ALL dices
  public int diceGetSumAll() {
    int sum = 0;
    for (int i = 0; i < dices.size(); i++) {
      sum += dices.get(i).getValue();
    }
    return sum;
  }

  public ArrayList<Dice> getDices() {
    return dices;
  }
}
