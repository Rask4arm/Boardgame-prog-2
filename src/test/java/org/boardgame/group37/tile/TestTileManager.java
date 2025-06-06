package org.boardgame.group37.tile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.boardgame.group37.model.tile.*;

public class TestTileManager {

    @Test
    public void testTileAddAndGet() {
        try {
        TileManager tileManager = new TileManager(10, 100, BOARDTYPES.SNAKE_AND_LADDERS);
        tileManager.tileAdd(new Tile());
        tileManager.tileAdd(new Tile());
        assertEquals(tileManager.getSizeRaw(), 102);
        assertEquals(tileManager.getSize(), 100);
        assertEquals(tileManager.getWidth(), 10);
        } catch (Exception e) {
            fail("TileManager generation failed: " + e.getMessage());
        }
    }

    @Test
    public void testTileGenerate() {
        try {
            TileManager tileManager = new TileManager(2, 6, BOARDTYPES.SNAKE_AND_LADDERS);
            assertEquals(tileManager.getSize(), tileManager.getSizeRaw());
            assertEquals(tileManager.getWidth(), 2);
            assertEquals(tileManager.getTiles().size(), 6);
        } catch (Exception e) {
            fail("TileManager generation failed: " + e.getMessage());
        }
    }

    @Test
    public void testTileGenerateMonopoly() {
        try {
        TileManager tileManager = new TileManager(2, 6, BOARDTYPES.MONOPOLY);
        assertEquals(tileManager.getSize(), tileManager.getSizeRaw());
        assertEquals(tileManager.getWidth(), 2);
        assertEquals(tileManager.getTiles().size(), 6);
        } catch (Exception e) {
            fail("TileManager generation failed: " + e.getMessage());
        }
    }

}
