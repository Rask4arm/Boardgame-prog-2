package org.boardgame.group37.model.tile.action;

import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerManager;

import java.util.Random;

/**
 * This class is responsible for the action of switching the positions of two players on the board.
 * It implements the Action interface and defines the action method.
 */
public class ActionSwitch implements Action {

    // Properties
    private PlayerManager playerManager;
    ActionSwitch(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public void execute(Player player) {
        // Switch the positions of the two players
        Player otherPlayer = player;
        while (otherPlayer == player) {
            otherPlayer = playerManager.getPlayers().get(new Random().nextInt(playerManager.getPlayers().size()));
        }
        
        System.out.println("Debug: ActionSwitch executed. Players switched positions.");
    }

}
