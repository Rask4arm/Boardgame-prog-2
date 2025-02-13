package org.boardgame.group37.backend.managers;

public class GameManager {

  private int playerTurnIndex = 0;
  private boolean gameStart = false;
  private boolean gameEnd = false;
  
  DiceManager diceManager = new DiceManager();
  PlayerManager playerManager = new PlayerManager();
  TileManager tileManager = new TileManager();

  public GameManager() {
  }



}
