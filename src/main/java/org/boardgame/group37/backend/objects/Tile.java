package org.boardgame.group37.backend.objects;

public class Tile {

  // Initiate variables
  private int tileIndex, tileTarget;
  private boolean isTeleport;

  // Constructor for teleporting tiles
  public Tile(int index, int target) {
    tileIndex = index;
    tileTarget = target;
    isTeleport = true;
  }

  // Constructor for normal tiles
  public Tile(int index) {
    tileIndex = index;
    tileTarget = index + 1;
    isTeleport = false;
  }

  // Getters
  public int getTileIndex() {
    return tileIndex;
  }

  public int getTileTarget() {
    return tileTarget;
  }

  public boolean isTeleport() {
    return isTeleport;
  }
}
