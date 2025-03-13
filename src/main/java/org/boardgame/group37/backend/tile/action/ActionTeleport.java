package org.boardgame.group37.backend.tile.action;

import org.boardgame.group37.backend.player.Player;

/*
 * ActionTeleport class is responsible for executing the teleport action of the tile.
 * The teleport action moves the player to the specified target tile.
 * If the target tile is the same as the current tile, a warning message is displayed.
 */
public class ActionTeleport implements Action {

    // Properties
    private int target = 0;

    /*
     * Constructor
     * Initializes the ActionTeleport with the specified target tile index.
     * @param target: target tile index
     */
    public ActionTeleport(int currentIndex) {
        this.target = currentIndex;
        System.err.println("Warning: Teleport action created with same target as current index.");
    }

    /*
     * Constructor
     * Initializes the ActionTeleport with the specified target tile index.
     * @param currentIndex: current tile index
     */
    public ActionTeleport(int currentIndex, int target) {
        this.target = target;
    }

    // Methods

    /*
     * execute method moves the player to the target tile.
     * @param player: Player object
     */
    @Override
    public void execute(Player player) {
        System.out.println("Debug: Executing teleport action");
        player.setPosition(target);
    };

    /*
     * getTarget method returns the target tile index.
     * @return target tile index
     */
    public int getTarget() {
        return target;
    }

    /*
     * toString method returns the name of the action.
     * @return name of the action
     */
    @Override
    public String toString() {
        return "ActionTeleport";
    }
}
