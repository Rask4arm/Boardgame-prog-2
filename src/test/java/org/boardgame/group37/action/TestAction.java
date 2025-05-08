package org.boardgame.group37.action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import javafx.scene.paint.Color;

import org.boardgame.group37.model.player.*;
import org.boardgame.group37.model.tile.action.*;

public class TestAction {

    @Test
    public void testAction() throws Exception{
        
        // Create player
        PlayerManager playerManager = new PlayerManager();
        playerManager.playerAdd(new Player(0));
        playerManager.playerAdd(new Player(1));
        Player[] players = {playerManager.getPlayers().get(0), playerManager.getPlayers().get(1)};
        
        // Create action types
        Action actionDefault = new ActionDefault();
        Action actionTeleport = new ActionTeleport(5);
        Action actionSwitch = new ActionSwitch();
        Action actionMOTile = new ActionMonopolyTile(500);

        // Give player manager
        ((ActionSwitch) actionSwitch).setPlayerManager(playerManager);
        ((ActionMonopolyTile) actionMOTile).setPlayerManager(playerManager);

        // Test actions
        assertEquals(players[0].getPosition(), 0);
        actionDefault.execute(players[0]);
        assertEquals(players[0].getPosition(), 1);
        actionTeleport.execute(players[0]);
        assertEquals(players[0].getPosition(), 5);

        actionSwitch.execute(players[0]);
        assertEquals(players[0].getPosition(), 0);
        assertEquals(players[1].getPosition(), 5);

        // Test monopoly tile action
        actionTeleport.execute(players[0]);
        actionMOTile.execute(players[0]);  
        assertEquals(players[0].getMoney(), 500);
        assertEquals(((ActionMonopolyTile) actionMOTile).getPlayerIndexOwner(), 0);
        actionMOTile.execute(players[1]);
        assertEquals(players[1].getMoney(), 750);
        assertEquals(players[0].getMoney(), 750);



        
    }

}
