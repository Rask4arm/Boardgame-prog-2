package org.boardgame.group37.backend;
import java.util.ArrayList;
import java.awt.Color;

public class PlayerManager {

    // Properties
    private ArrayList<Player> players = new ArrayList<>();

    // Constructor
    public PlayerManager() {
        System.out.println("\nDebug: PlayerManager created.");
    }

    // Methods
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void playerAdd() {
        players.add(new Player());
        System.out.println(String.format("Debug: Current number of players: %d", players.size()));
    }

    public void playerAdd(Color color, String name) {
        players.add(new Player(color, name));
        System.out.println(String.format("Debug: Current number of players: %d", players.size()));
    }

    public void playerRemove() {
        players.remove(players.size() - 1);
        System.out.println(String.format("Debug: Player removed. Current number of players: %d", players.size()));
    }
}
