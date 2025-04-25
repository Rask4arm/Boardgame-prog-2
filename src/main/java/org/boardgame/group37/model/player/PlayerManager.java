package org.boardgame.group37.model.player;
import java.util.ArrayList;
import java.awt.Color;

/**
 * PlayerManager class is responsible for managing player data.
 * It stores an ArrayList of Player objects.
 */
public class PlayerManager {

    // Properties
    private ArrayList<Player> players = new ArrayList<>();

   /**
    * Constructor
    * Initializes the PlayerManager with an empty ArrayList of Player objects.
    */
    public PlayerManager() {
        System.out.println("\nDebug: PlayerManager created.");
    }

    // Methods

    /**
     * getPlayers method returns the ArrayList of Player objects.
     * @return ArrayList of Player objects
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * playerAdd method adds a new Player object to the ArrayList.
     * The player is initialized with default values.
     */
    public void playerAdd() {
        players.add(new Player());
        System.out.println(String.format("Debug: Current number of players: %d", players.size()));
    }

    /**
     * playerAdd method adds a new Player object to the ArrayList.
     * The player is initialized with the specified color and name.
     * @param color: Player color
     * @param name: Player name
     */
    public void playerAdd(String name, Color color) {
        players.add(new Player(name, color));
        System.out.println(String.format("Debug: Current number of players: %d", players.size()));
    }

    /**
     * playerAdd method adds a new Player object to the ArrayList.
     * The player is initialized with the specified Player object.
     * @param player Player object
     */
    public void playerAdd(Player player) {
        players.add(player);
        System.out.println(String.format("Debug: Current number of players: %d", players.size()));
    }

    /**
     * playerRemove method removes the last Player object from the ArrayList.
     */
    public void playerRemove() {
        players.remove(players.size() - 1);
        System.out.println(String.format("Debug: Player removed. Current number of players: %d", players.size()));
    }
}
