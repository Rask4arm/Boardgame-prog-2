package org.boardgame.group37.model.player;
import java.util.ArrayList;
import javafx.scene.paint.Color;

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
     * getPlayer method returns the Player object at the specified index.
     * @param index index of the player
     * @return Player object
     */

    public Player getPlayer(int index) {
        return players.get(index);
    }

    /**
     * playerAdd method adds a new Player object to the ArrayList.
     * The player is initialized with default values.
     */
    public void playerAdd() {
        players.add(new Player(Integer.valueOf(players.size())));
        System.out.println(String.format("Debug: Current number of players: %d", players.size()));
    }

    /**
     * playerAdd method adds a new Player object to the ArrayList.
     * The player is initialized with the specified color and name.
     * @param color: Player color
     * @param name: Player name
     */
    public void playerAdd(String name, Color color) {
        players.add(new Player(name, color, Integer.valueOf(players.size())));
        System.out.println(String.format("Debug: Current number of players: %d", players.size()));
    }

    /**
     * playerAdd method adds a new Player object to the ArrayList.
     * The player is initialized with the specified Player object.
     * @param player Player object
     */
    public void playerAdd(Player player) {
        player.setIndex(Integer.valueOf(players.size()));
        players.add(player);
        System.out.println(String.format("Debug: Current number of players: %d", players.size()));
    }

    /**
     * playerRemove method removes the last Player object from the ArrayList.
     */
    public void playerRemove() {
        players.removeLast();
        System.out.println(String.format("Debug: Player removed. Current number of players: %d", players.size()));
    }

    /**
     * playerRemove method removes the specified Player object from the ArrayList.
     * @param index index of the player to be removed
     */
    public void playerRemove(int index) {
        players.remove(index);
        System.out.println(String.format("Debug: Player removed. Current number of players: %d", players.size()));
    }

    /**
     * changePlayerName method changes the name of the specified Player object.
     * @param index index of the player
     * @param name new name of the player
     */
    public void changePlayerName(int index, String name) {
        players.get(index).setName(name);
        System.out.println(String.format("Debug: Player %d name changed to %s", index, name));
    }

    /**
     * changePlayerColor method changes the color of the specified Player object.
     * @param index index of the player
     * @param color new color of the player
     */
    public void changePlayerColor(int index, Color color) {
        players.get(index).setColor(color);
        System.out.println(String.format("Debug: Player %d color changed to %s", index, color));
    }
}
