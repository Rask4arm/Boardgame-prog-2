package org.boardgame.group37.action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.boardgame.group37.model.player.*;
import org.boardgame.group37.model.tile.action.*;
import javafx.scene.paint.Color;

public class TestAction {

    @Test
    public void testAction() throws Exception{
        
        // Create player
        PlayerManager playerManager = new PlayerManager();
        playerManager.playerAdd("p1", Color.RED);
        playerManager.playerAdd("p2", Color.BLUE);
        Player player1 = playerManager.getPlayers().get(0);
        Player player2 = playerManager.getPlayers().get(1);
        
        // Create action types
        Action actionDefault = new ActionDefault();
        Action actionTeleport = new ActionTeleport(5);
        Action actionMonopolyTile = new ActionMonopolyTile(100);
        ((ActionMonopolyTile) actionMonopolyTile).setPlayerManager(playerManager);

        // Test snake and ladder action
        assertEquals(player1.getPosition(), 0);
        actionDefault.execute(player1);
        assertEquals(player1.getPosition(), 1);
        actionTeleport.execute(player1);
        assertEquals(player1.getPosition(), 5);

        // Test monopoly action
        actionMonopolyTile.execute(player2);
        assertEquals(player2.getMoney(), 900);

        actionMonopolyTile.execute(player1);
        assertEquals(player1.getMoney(), 950);
        assertEquals(player2.getMoney(), 950);


        
    }

}
