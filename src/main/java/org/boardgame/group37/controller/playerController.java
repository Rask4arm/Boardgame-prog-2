package org.boardgame.group37.controller;
import org.boardgame.group37.model.player.*;
import org.boardgame.group37.model.game.*;

public class playerController {

    public void addPlayer(GameManager gameManager, Player player) {
        gameManager.getPlayerManager().playerAdd(player);
    }    

    public void removePlayer(GameManager gameManager) {
        gameManager.getPlayerManager().playerRemove();
    }

}
