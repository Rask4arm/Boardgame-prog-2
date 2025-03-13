package org.boardgame.group37.backend.tile.action;

import org.boardgame.group37.backend.player.Player;

/*
 * Action interface is responsible for executing the action of the tile.
 */
public interface Action {

    /*
     * execute method executes the action of the tile.
     * @param player: Player object
     */
    public void execute(Player player);

}
