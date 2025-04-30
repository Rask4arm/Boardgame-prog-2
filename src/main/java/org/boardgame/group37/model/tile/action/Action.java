package org.boardgame.group37.model.tile.action;

import org.boardgame.group37.model.player.Player;
import java.awt.Color;


/**
 * Action interface is responsible for executing the action of the tile.
 */
public interface Action {

    public Color getColor();
    public void execute(Player player) throws Exception;

}
