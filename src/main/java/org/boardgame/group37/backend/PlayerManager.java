package org.boardgame.group37.backend;
import java.util.ArrayList;

public class PlayerManager {

    // Properties
    private ArrayList<Player> players = new ArrayList<>();

    // Constructor
    public PlayerManager() {

    }

    // Methods
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void playerAdd() {
        players.add(new Player());
    }

    public void playerRemove() {
        players.remove(players.size() - 1);
    }
}
