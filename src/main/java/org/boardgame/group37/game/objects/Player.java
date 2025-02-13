package main.java.org.boardgame.group37.game.objects;

public class Player {
  private int tileIndex = 0;
  private int tileTarget = 0;
  private int playerIndex = 0;
  
  public Player() {
  }

  // Move player forward one tile
  void tileMove() {
    tileIndex++;
  }

  // Move player to a specific tile
  void tileTeleport(int index) {
    tileIndex = index;
  }

  public int getTileIndex() {
    return tileIndex;
  }

  public int getTileTarget() {
    return tileTarget;
  }

  public void setTileTarget(int target) {
    tileTarget = target;
  }

  public int getPlayerIndex() {
    return playerIndex;
  }

  public void setPlayerIndex(int index) {
    playerIndex = index;
  }

}
