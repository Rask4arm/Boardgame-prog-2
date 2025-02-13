package org.boardgame.group37.backend.managers;
import org.boardgame.group37.backend.objects.Player;
import java.util.ArrayList;
import java.awt.Color;

public class PlayerManager {

  // Player list
  private ArrayList<Player> players = new ArrayList<Player>();

  // Constructor
  public PlayerManager() {
  }

  // Methods
  // Get the first empty player index
  public int playerGetFreeIndex() {
    for (int i = 0; i < players.size(); i++) {
      if (players.get(i) == null) {
        return i;
      }
    }
    return players.size();
  }

  // Add player to player list
  public void playerAdd(Player player) {
    int indexSet = playerGetFreeIndex();
    Player playerNew = new Player(indexSet, playerGetColor(indexSet));
    players.add(playerNew);
  }

  // Remove player based on index
  public void playerRemove(int playerIndex) {
    for (int i = 0; i < players.size(); i++) {
      if (players.get(i).getPlayerIndex() == playerIndex) {
        players.remove(i);
        break;
      }
    }
  }

  // Get player color based on index
  public Color playerGetColor(int playerIndex) {
    switch (playerIndex) {
      case 0: return Color.RED;
      case 1: return Color.BLUE;
      case 2: return Color.GREEN;
      case 3: return Color.YELLOW;
      case 4: return Color.ORANGE;
      case 5: return Color.PINK;
      case 6: return Color.CYAN;
      case 7: return Color.MAGENTA;
      default: return Color.BLACK;
    }
  }

  // Get all players at a set tile index
  public ArrayList<Player> playersGetAtTile (int tileIndex) {
    ArrayList<Player> playersAtTile = new ArrayList<Player>();
    for (Player player : players) {
      if (player.getTileIndex() == tileIndex) {
        playersAtTile.add(player);
      }
    }
    return playersAtTile;
  }
  
  // Getters
  public ArrayList<Player> getPlayers() {
    return players;
  }
}
