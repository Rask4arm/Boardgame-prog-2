package org.boardgame.group37.backend.tile.action;

import org.boardgame.group37.backend.player.Player;

/**
 * ActionDefault class is responsible for executing the default action of the tile.
 * The default action is moving the player one step forward.
 */
public class ActionDefault implements Action{

    /**
     * execute method moves the player one step forward.
     * @param player: Player object
     */
    @Override
    public void execute(Player player) {
        player.executeMove();
        System.out.println("Debug: ActionDefault executed");
    };

    /**
     * toString method returns the name of the action.
     * @return name of the action
     */
    @Override
    public String toString() {
        return "ActionDefault";
    }
}
