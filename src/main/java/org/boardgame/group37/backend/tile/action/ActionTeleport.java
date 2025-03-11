package org.boardgame.group37.backend.tile.action;

import org.boardgame.group37.backend.player.Player;

public class ActionTeleport implements Action {

    // Properties
    private int target = 0;

    // Constructor
    public ActionTeleport(int currentIndex) {
        this.target = currentIndex;
        System.err.println("Warning: Teleport action created with same target as current index.");
    }

    public ActionTeleport(int currentIndex, int target) {
        this.target = target;
    }

    // Methods
    public void execute(Player player) {
        System.out.println("Debug: Executing teleport action");
        player.setPosition(target);
    };

    public int getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "ActionTeleport";
    }
}
