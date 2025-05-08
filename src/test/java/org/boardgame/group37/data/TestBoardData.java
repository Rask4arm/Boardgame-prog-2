package org.boardgame.group37.data;
import org.boardgame.group37.model.player.PlayerDataManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.boardgame.group37.model.tile.*;
import org.boardgame.group37.model.tile.action.*;


public class TestBoardData {

    @Test
    public void testBoardData() throws Exception {

        // Create board data
        TileManager tileManager = new TileManager(10, 100, BOARDTYPES.SNAKE_AND_LADDERS);

        // Save and load board data
        TileDataManager.dataSave(tileManager, "test_board.json");
        TileManager tileLoad = TileDataManager.dataLoad("test_board.json");

        System.out.println(tileLoad);

        // Test board data
        assertEquals(
            tileManager.getTiles().size(), tileLoad.getTiles().size(), 
            "The number of tiles should be the same after loading."
        );

        assertEquals(
            tileManager.getTiles().get(0).getAction().getClass(),
            tileLoad.getTiles().get(0).getAction().getClass()
        );
        assertEquals(
            tileManager.getTiles().size(), tileLoad.getTiles().size()
        );
        TileDataManager.dataDeleteAll();
    }

    @Test void testBoardDataMonopoly() throws Exception {

        // Create board data
        TileManager tileManager = new TileManager(10, 30, BOARDTYPES.MONOPOLY);

        // Save and load board data
        TileDataManager.dataSave(tileManager, "test_board.json");
        TileManager tileLoad = TileDataManager.dataLoad("test_board.json");

        System.out.println(tileLoad);

        // Test board data
        assertEquals(
            tileManager.getTiles().size(), tileLoad.getTiles().size(), 
            "The number of tiles should be the same after loading."
        );

        assertEquals(
            tileManager.getTiles().get(0).getAction().getClass(),
            tileLoad.getTiles().get(0).getAction().getClass()
        );
        TileDataManager.dataDeleteAll();
    }

}
