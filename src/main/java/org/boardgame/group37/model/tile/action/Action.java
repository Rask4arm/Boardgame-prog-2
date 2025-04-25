package org.boardgame.group37.model.tile.action;

import org.boardgame.group37.model.player.Player;

/**
 * Action interface is responsible for executing the action of the tile.
 */
public interface Action {

    public void execute(Player player);

}
