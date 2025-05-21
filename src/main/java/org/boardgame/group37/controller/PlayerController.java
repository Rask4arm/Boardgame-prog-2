package org.boardgame.group37.controller;
import javafx.scene.paint.Color;
import org.boardgame.group37.model.player.*;
import org.boardgame.group37.view.ColorPalette;

import java.util.ArrayList;

/**
 * PlayerController class
 * This class is responsible for managing players in the game.
 */
public class PlayerController {
    private PlayerManager playerManager;

    /**
     * Constructor for PlayerController
     */
    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Constructor for PlayerController with PlayerManager
     * @param playerManager the PlayerManager to be set
     */
    public void setStartPlayers() {
        Color colorP1 = ColorPalette.PLAYER_RED;
        Color colorP2 = ColorPalette.PLAYER_BLUE;
        playerManager.playerAdd("Player 1", colorP1);
        playerManager.playerAdd("Player 2", colorP2);
    }

    /**
     * Adds a player to the game
     * @param name the name of the player
     * @param color the color of the player
     */
    public void addPlayer(String name, Color color) {
        playerManager.playerAdd(name, color);
    }    

    /**
     * Removes a player from the game
     * @param index the index of the player to be removed
     */
    public void removePlayer(int index) {
        playerManager.playerRemove(index);
    }

    /**
     * Gets the PlayerManager
     * @return PlayerManager
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    /**
     * Changes the color of a player
     * @param index the index of the player
     * @param color the new color of the player
     */
    public void changePlayerColor(int index, Color color) {
        playerManager.changePlayerColor(index, color);
    }

    /**
     * Changes the name of a player
     * @param index the index of the player
     * @param name the new name of the player
     */
    public void changePlayerName(int index, String name) {
        playerManager.changePlayerName(index, name);
    }

    /**
     * Gets the player by specified index
     * @param index the index of the player
     * @return the player at the specified index
     */
    public Player getPlayer(int index) {
        return playerManager.getPlayer(index);
    }

    /**
     * Gets the number of players
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return playerManager.getPlayers().size();
    }

    /**
     * Gets the players
     * @return the list of players
     */
    public ArrayList<Player> getPlayers() {
        return playerManager.getPlayers();
    }
}
