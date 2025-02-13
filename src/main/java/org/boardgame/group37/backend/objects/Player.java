package org.boardgame.group37.backend.objects;
import java.awt.Color;

public class Player {

  // Player attributes
  private int tileIndex = 0;
  private int playerIndex = -1;
  private Color playerColor = Color.BLACK;
  
  // Constructor
  public Player(int playerIndex, Color playerColor) {
    this.playerIndex = playerIndex;
    this.playerColor = playerColor;
  }

  // Methods
  // Move player forward one tile
  public void tileMove(Tile tile) {
    tileIndex = tile.getTileTarget();
  }

  // Getters
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
