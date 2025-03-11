package org.boardgame.group37.backend.tile.action;

import org.boardgame.group37.backend.player.Player;

public class ActionDefault implements Action{

    public void execute(Player player) {
        player.executeMove();
        System.out.println("Debug: ActionDefault executed");
    };

    @Override
    public String toString() {
        return "ActionDefault";
    }
}
