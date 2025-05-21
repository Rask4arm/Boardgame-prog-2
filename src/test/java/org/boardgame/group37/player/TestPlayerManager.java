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
        playerManager.playerAdd(new Player("test1", Color.RED, 0));
        assertEquals(playerManager.getPlayers().size(), 2);
        playerManager.playerAdd("test2", Color.BLUE);
        assertEquals(playerManager.getPlayers().size(), 3);
        playerManager.playerRemove(0);
        assertEquals(playerManager.getPlayers().size(), 2);
        assertEquals(playerManager.getPlayers().get(1).getName(), "test2");
    }

    @Test
    public void testRemovePlayerOutOfBoundsThrowsException() {
        PlayerManager playerManager = new PlayerManager();
        playerManager.playerAdd("A", Color.RED);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            playerManager.playerRemove(5);
        });
    }

}
