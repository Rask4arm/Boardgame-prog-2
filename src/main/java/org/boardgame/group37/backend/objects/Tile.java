public class Tile {

  private int tileIndex, tileTarget;
  private boolean isTeleport;

  public Tile(int index, int target) {
    tileIndex = index;
    tileTarget = target;
    isTeleport = true;
  }

  public Tile(int index) {
    tileIndex = index;
    tileTarget = index + 1;
    isTeleport = false;
  }

  public int getTileIndex() {
    return tileIndex;
  }

  public int getTileTarget() {
    return tileTarget;
  }
}
