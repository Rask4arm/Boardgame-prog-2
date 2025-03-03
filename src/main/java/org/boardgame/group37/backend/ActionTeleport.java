package org.boardgame.group37.backend;

public class ActionTeleport implements Action {

    // Properties
    private int target = 0;

    // Constructor
    public ActionTeleport(int target) {
        this.target = target;
    }

    // Methods
    public void execute(Player player) {
        // Do nothing
    };

    public int getTarget() {
        return target;
    }
}
