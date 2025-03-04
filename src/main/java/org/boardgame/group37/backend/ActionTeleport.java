package org.boardgame.group37.backend;

public class ActionTeleport implements Action {

    // Properties
    private int target = 0;

    // Constructor
    public ActionTeleport(int currentIndex) {
        this.target = currentIndex;
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
}
