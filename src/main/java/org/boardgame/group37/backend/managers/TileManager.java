package org.boardgame.group37.backend.managers;
import org.boardgame.group37.backend.objects.Tile;
import java.util.ArrayList;
import java.lang.Math;

public class TileManager {
  
  // Variables
  private ArrayList<Tile> board = new ArrayList<Tile>(); // Board of tiles
  private int tileCount, boardWidth; // Number of tiles and width of board

  // Constructor
  public TileManager(int tileCount, int boardWidth) {
    this.tileCount = tileCount;
    this.boardWidth = boardWidth;
  }

  // Methods
  // Generates the board
  public void boardGenerate() {
    board.clear();
    for (int i = 0; i < tileCount; i++) {

      // % chance to add random target tile
      boolean isRandomTarget = Math.random() < 0.1;

      // Add random target tile
      if (isRandomTarget) {
        board.add(new Tile(i, tileGetRandomTarget(i)));
      }

      // Add normal tile
      else {
        board.add(new Tile(i));
      }

    }
  }

  // Selects a random tile with a gap +- 10 from original index
  private int tileGetRandomTarget(int originalIndex) {
    int randomIndex = originalIndex + (int)(Math.random() * 21) - 10;
    randomIndex = Math.max(0, Math.min(randomIndex, tileCount - 1));
    return randomIndex;
  }

  // Getters
  public ArrayList<Tile> getBoard() {
    return board;
  }

  public int getBoardWidth() {
    return boardWidth;
  }

  public int getTileCount() {
    return tileCount;
  }
  
}
