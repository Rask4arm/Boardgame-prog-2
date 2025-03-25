package org.boardgame.group37.data;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.boardgame.group37.backend.tile.*;
import java.util.ArrayList;


public class TestBoardData {

    @Test
    public void testBoardData() throws Exception {

        // Create board data
        TileManager tileManager = new TileManager();
        tileManager.tileAdd(new Tile());
        tileManager.tileAdd(new Tile());

        // Save and load board data
        TileDataManager.dataSave(tileManager.getTiles(), "test_board.json");
        TileManager tileLoad = new TileManager(TileDataManager.dataLoad("test_board.json"));

        System.out.println(tileLoad);

        // Test board data
        assertTrue(tileLoad.getTiles().get(0).getAction().toString().equals(
            tileManager.getTiles().get(0).getAction().toString())
        );
        

    }
}
