package org.boardgame.group37.backend.objects;
import org.boardgame.group37.backend.objects.Tile;
import java.awt.Color;

public class Player {
  private int tileIndex = 0;
  private int playerIndex = -1;
  private Color playerColor = Color.BLACK;
  
  public Player(int playerIndex, Color playerColor) {
    this.playerIndex = playerIndex;
    this.playerColor = playerColor;
  }

  // Move player forward one tile
  void tileMove(Tile tile) {
    tileIndex = tile.getTileTarget();
  }

  public int getTileIndex() {
    return tileIndex;
  }

  public int getPlayerIndex() {
    return playerIndex;
  }

  public Color getPlayerColor() {
    return playerColor;
  }

}
