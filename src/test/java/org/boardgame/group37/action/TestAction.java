package org.boardgame.group37.action;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.boardgame.group37.model.player.*;
import org.boardgame.group37.model.tile.action.*;

public class TestAction {

    @Test
    public void testAction() throws Exception{
        
        // Create player
        Player player = new Player(0);
        
        // Create action types
        Action actionDefault = new ActionDefault();
        Action actionTeleport = new ActionTeleport(5);

        // Test actions
        assertEquals(player.getPosition(), 0);
        actionDefault.execute(player);
        assertEquals(player.getPosition(), 1);
        actionTeleport.execute(player);
        assertEquals(player.getPosition(), 5);
        
    }

}
