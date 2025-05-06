package org.boardgame.group37.model.tile.action;

import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerManager;

import java.util.Random;
import javafx.scene.paint.Color;

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
    public void execute(Player player) throws Exception {
        // Switch the positions of the two players
        Player otherPlayer = player; int i = 0;
        while (otherPlayer == player) {
            otherPlayer = playerManager.getPlayers().get(new Random().nextInt(playerManager.getPlayers().size()));
            
            // Error handling: if we can't find another player after 100 tries, throw an exception
            i++; if (i > 100) {
                throw new Exception("Error: Unable to find another player to switch with.");
            }
        }
        
        System.out.println("Debug: ActionSwitch executed. Players switched positions.");
    }

    /**
     * getColor method returns the color of the action.
     *
     * @return color of the action
     */
    @Override
    public javafx.scene.paint.Color getColor() {
        return Color.LIGHTGRAY;
    }
}
