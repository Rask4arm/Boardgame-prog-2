package org.boardgame.group37.data;
import org.boardgame.group37.model.player.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;


public class TestPlayerData {

    @Test
    public void testAdd() throws Exception {
        Player player = new Player(0);
        PlayerDataManager.dataSave(player);
        Player Player1 = PlayerDataManager.dataLoad(player.getName());
        assertEquals(player.getName(), Player1.getName());
    }

    @Test
    public void testDelete() throws Exception {
        Player[] players = {
            new Player("test1", Color.BLUE, 0), 
            new Player("test2", Color.RED, 1), 
            new Player("test3", Color.GREEN, 2)
        };

        // Rewrite all data
        PlayerDataManager.dataDeleteAll();
        for (Player player : players) {
            PlayerDataManager.dataSave(player);
        }

        PlayerDataManager.dataDelete(players[1].getName());
        assertEquals(players[0].getName(), PlayerDataManager.dataLoad(players[0].getName()).getName());
        assertNull(PlayerDataManager.dataLoad(players[1].getName()));
        assertEquals(players[2].getName(), PlayerDataManager.dataLoad(players[2].getName()).getName());

        PlayerDataManager.dataDeleteAll();
    }

}
