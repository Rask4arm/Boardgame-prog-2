package main.java.org.boardgame.group37.game.objects;
import main.java.org.boardgame.group37.game.objects.Tile;
import main.java.org.boardgame.group37.game.objects.Color;

public class Player {
  private int tileIndex = 0;
  private int tileTarget = 0;
  private int playerIndex = -1;
  private Color playerColor = Color.BLACK;
  
  public Player() {
  }

  // Move player forward one tile
  void tileMove(Tile tile) {
    tileIndex = tile.getTileTarget();
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
    playerColor = playerGetColor(index);
  }

}
