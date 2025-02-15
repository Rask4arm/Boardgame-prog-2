package org.boardgame.group37.backend.managers;



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

  // Method game
  // Game start
  public void gameStart() {
    gameStart = true;
    roundStart();
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

  /* Method round

   * round order:
   * 1. Start turn
   * 2. Roll dice
   * 3. Move player
   * 4. End turn
   * 
  */

  // Round start
  public void roundStart() {
    gameState = "Start";
  }

  // Round roll
  public void roundRoll() {
    diceManager.diceRollAll();
    moveCount = diceManager.diceGetSumAll();
    gameState = "Roll";
  }

  // Round move
  public void roundMove() {
    boolean executeAction = (moveCount == 1) ? true : false;
    playerManager.getPlayers().get(playerTurnIndex).tileMove( // execute tileMove method from Player class
      tileManager.getBoard().get(
        playerManager.getPlayers().get(playerTurnIndex).getTileIndex() // get tileindex from player
      )
    , executeAction
    );
    moveCount--;
    gameState = (moveCount > 0) ? "Move" : "Turn";
  }

  // Round end
  public void roundEnd() {
    gameNextTurn();
    roundStart();
  }

  // Getters
  // Index of player index whose turn it is
  public int getPlayerCurrentIndex() {
    return playerManager.getPlayers().get(playerTurnIndex).getPlayerIndex();
  }

  // List index of player whose turn it is
  public int getPlayerTurnIndex() {
    return playerTurnIndex;
  }

  public boolean getGameStart() {
    return gameStart;
  }

  public boolean getGameEnd() {
    return gameEnd;
  }

  public String getGameState() {
    return gameState;
  }

}
