package main.java.org.boardgame.group37.backend.managers;
import java.util.ArrayList;
import main.java.org.boardgame.group37.game.objects.Dice;

public class DiceManager {
  private ArrayList<Dice> dices = new ArrayList<Dice>();

  public DiceManager() {
  }

  public void diceAdd() {
    dices.add(dice);
  }

  public void diceRemove() {
    if (!dices.isEmpty()) {
      dices.remove(dices.size() - 1);
    }
  }

  public void diceRoll() {
    for (int i = 0; i < dices.size(); i++) {
      dices.get(i).roll();
    }
  }

  public int diceGetSum() {
    int sum = 0;
    for (int i = 0; i < dices.size(); i++) {
      sum += dices.get(i).getValue();
    }
    return sum;
  }
}
