package org.boardgame.group37.player;
import org.boardgame.group37.model.player.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;

public class TestPlayerManager {

    @Test 
    public void testAddRemovePlayer() {
        PlayerManager playerManager = new PlayerManager();
        assertEquals(playerManager.getPlayers().size(), 0);
        playerManager.playerAdd();
        assertEquals(playerManager.getPlayers().size(), 1);
        playerManager.playerAdd(new Player("test1", Color.RED, null));
        assertEquals(playerManager.getPlayers().size(), 2);
        playerManager.playerAdd("test3", Color.BLUE);
        assertEquals(playerManager.getPlayers().size(), 3);
        playerManager.playerRemove();
        playerManager.playerRemove(0);
        assertEquals(playerManager.getPlayers().size(), 1);
        assertEquals(playerManager.getPlayers().get(0).getName(), "test1");
    }

}
