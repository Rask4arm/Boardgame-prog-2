package org.boardgame.group37.backend.managers;

public class GameManager {

  // Variables
  private int playerTurnIndex = 0;
  private boolean gameStart = false;
  private boolean gameEnd = false;
  private String gameState = "Menu";
  
  // Managers
  DiceManager diceManager = new DiceManager();
  PlayerManager playerManager = new PlayerManager();
  TileManager tileManager = new TileManager(80, 10);

  // Constructor
  public GameManager() {
  }

  // Methods
  // Game start
  public void gameStart() {
    gameStart = true;
    gameState = "Turn";
  }

  public void waitForButtonPress(Button button) {
    final Object lock = new Object();
    button.setOnAction(event -> {
      synchronized (lock) {
        lock.notify();
      }
    });

    synchronized (lock) {
      try {
        lock.wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  // Game end
  public void gameEnd() {
    gameEnd = true;
  }

  // Game reset
  public void gameReset() {
    gameStart = false;
    gameEnd = false;
    playerTurnIndex = 0;
    diceManager.getDices().clear();
    playerManager.getPlayers().clear();
    tileManager.boardGenerate();
  }

}
