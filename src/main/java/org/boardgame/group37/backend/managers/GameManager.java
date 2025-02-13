package main.java.org.boardgame.group37;

public class GameManager {
  private int playerTurnIndex = 0;
  DiceManager diceManager = new DiceManager();
  PlayerManager playerManager = new PlayerManager();
  TileManager tileManager = new TileManager();

  public GameManager() {
  }

}
