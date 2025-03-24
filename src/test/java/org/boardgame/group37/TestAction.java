package org.boardgame.group37;
import org.junit.jupiter.api.Test;
import org.boardgame.group37.backend.tile.action.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.boardgame.group37.backend.player.*;

public class TestAction {

    @Test
    public void testAction() {
        
        // Create player
        Player player = new Player();
        
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
