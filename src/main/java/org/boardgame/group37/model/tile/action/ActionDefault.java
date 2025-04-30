package org.boardgame.group37.model.tile.action;

import org.boardgame.group37.model.player.Player;
import java.awt.Color;

/**
 * ActionDefault class is responsible for executing the default action of the tile.
 * The default action is moving the player one step forward.
 */
public class ActionDefault implements Action{

    /**
     * getColor method returns the color of the action.
     * @return color of the action
     */
    @Override
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }


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
