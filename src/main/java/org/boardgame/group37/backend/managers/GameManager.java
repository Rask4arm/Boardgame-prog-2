package org.boardgame.group37.backend.managers;
Playe playernow = playernow
TIle tilenow = tileNOw



public class GameManager {

  // Variables
  private int playerTurnIndex = 0;
  private boolean gameStart = false;
  private boolean gameEnd = false;
  private String gameState = "Menu";
  private int moveCount = 0;
  
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

  // Next players turn
  public void gameNextTurn() {
    playerTurnIndex++;
    if (playerTurnIndex >= playerManager.playerGetCount()) {
      playerTurnIndex = 0;
    }
  }

  /*
   * round order:
   * 
   * 1. Start turn
   * 2. Roll dice
   * 3. Move player
   * 
  */

  public void roundStart() {
    gameState = "Start";
  }

  public void roundRoll() {
    diceManager.diceRollAll();
    moveCount = diceManager.diceGetSumAll();
    gameState = "Roll";
  }

  public void roundMove() {
    while(moveCount > 0) {
      playerManager.getPlayers().get(playerTurnIndex).tileMove(
        tileManager.getBoard().get(
          playerManager.getPlayers().get(playerTurnIndex).getTileIndex()
        )
      );
      moveCount--;
    }
    gameState = "Move";
  }

}
