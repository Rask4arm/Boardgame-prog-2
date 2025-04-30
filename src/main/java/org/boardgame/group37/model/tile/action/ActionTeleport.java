package org.boardgame.group37.model.tile.action;

import org.boardgame.group37.model.player.Player;
import java.awt.Color;

/*
 * ActionTeleport class is responsible for executing the teleport action of the tile.
 * The teleport action moves the player to the specified target tile.
 * If the target tile is the same as the current tile, a warning message is displayed.
 */
public class ActionTeleport implements Action {

    // Properties
    private int targetIndex = 0;

    /**
     * Constructor
     * Initializes the ActionTeleport with the specified target tile index.
     * @param targetIndex: int target tile index
     */
    public ActionTeleport(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    // Methods

    /**
     * execute method moves the player to the target tile.
     * @param player: Player object
     */
    @Override
    public void execute(Player player) {
        System.out.println("Debug: Executing teleport action");
        player.setPosition(targetIndex);
    };

    /**
     * getTarget method returns the target tile index.
     * @return target tile index
     */
    public int getTarget() {
        return targetIndex;
    }

    /**
     * toString method returns the name of the action.
     * @return name of the action
     */
    @Override
    public String toString() {
        return "ActionTeleport";
    }

    /**
     * getColor method returns the color of the action.
     * @return color of the action
     */
    @Override
    public Color getColor() {
        return Color.DARK_GRAY;
    }
}
