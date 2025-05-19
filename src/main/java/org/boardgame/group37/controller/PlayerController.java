package org.boardgame.group37.controller;
import javafx.scene.paint.Color;
import org.boardgame.group37.model.player.*;
import org.boardgame.group37.model.game.*;
import org.boardgame.group37.view.ColorPalette;

import java.util.ArrayList;

public class PlayerController {
    private PlayerManager playerManager;

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    public void setStartPlayers() {
        Color colorP1 = ColorPalette.PLAYER_RED;
        Color colorP2 = ColorPalette.PLAYER_BLUE;
        playerManager.playerAdd("Player 1", colorP1);
        playerManager.playerAdd("Player 2", colorP2);
    }

    public void addPlayer(String name, Color color) {
        playerManager.playerAdd(name, color);
    }    

    public void removePlayer(int index) {
        playerManager.playerRemove(index);
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void changePlayerColor(int index, Color color) {
        playerManager.changePlayerColor(index, color);
    }

    public void changePlayerName(int index, String name) {
        playerManager.changePlayerName(index, name);
    }

    public Player getPlayer(int index) {
        return playerManager.getPlayer(index);
    }

    public int getNumberOfPlayers() {
        return playerManager.getPlayers().size();
    }


    public ArrayList<Player> getPlayers() {
        return playerManager.getPlayers();
    }
}
