package org.boardgame.group37.backend.managers;

public class GameManager {

  // Variables
  private int playerTurnIndex = 0;
  private boolean gameStart = false;
  private boolean gameEnd = false;
  
  // Managers
  DiceManager diceManager = new DiceManager();
  PlayerManager playerManager = new PlayerManager();
  TileManager tileManager = new TileManager(80, 10);

  // Constructor
  public GameManager() {
  }

  // Methods
  public void gameStart() {
    gameStart = true;
  }

  public void gameEnd() {
    gameEnd = true;
  }

  public void gameReset() {
    gameStart = false;
    gameEnd = false;
    playerTurnIndex = 0;
    diceManager.getDices().clear();
    playerManager.getPlayers().clear();
    tileManager.boardGenerate();
  }



}
