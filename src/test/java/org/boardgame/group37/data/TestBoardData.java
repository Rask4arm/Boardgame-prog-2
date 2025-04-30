package org.boardgame.group37.data;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.boardgame.group37.model.tile.*;
import org.boardgame.group37.model.tile.action.ActionTeleport;
import org.boardgame.group37.model.tile.action.*;


public class TestBoardData {

    @Test
    public void testBoardData() throws Exception {

        // Create board data
        TileManager tileManager = new TileManager();
        tileManager.tileAdd(new Tile());
        tileManager.tileAdd(new Tile());
        tileManager.tileAdd(new Tile());
        tileManager.tileAdd(new Tile(new ActionTeleport(5)));

        // Save and load board data
        TileDataManager.dataSave(tileManager, "test_board.json");
        TileManager tileLoad = TileDataManager.dataLoad("test_board.json");

        System.out.println(tileLoad);

        // Test board data
        assertEquals(
            tileManager.getTiles().size(), tileLoad.getTiles().size(), 
            "The number of tiles should be the same after loading."
        );
    }
}
