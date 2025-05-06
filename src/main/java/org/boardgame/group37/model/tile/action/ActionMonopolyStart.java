package org.boardgame.group37.model.tile.action;

import javafx.scene.paint.Color;
import org.boardgame.group37.model.player.Player;

public class ActionMonopolyStart implements Action {
    /**
     * getColor method returns the color of the action.
     *
     * @return color of the action
     */
    @Override
    public Color getColor() {
        return Color.GREEN;
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
}
