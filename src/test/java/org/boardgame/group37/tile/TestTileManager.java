package org.boardgame.group37.tile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.boardgame.group37.model.tile.*;

public class TestTileManager {

    @Test
    public void testTileAddAndGet() {
        TileManager tileManager = new TileManager();
        tileManager.tileAdd(new Tile());
        tileManager.tileAdd(new Tile());
        assertEquals(tileManager.getSizeRaw(), 2);
        assertEquals(tileManager.getSize(), 0);
        assertEquals(tileManager.getWidth(), 0);
    }

    @Test
    public void testTileGenerate() {
        TileManager tileManager = new TileManager(2, 6, BOARDTYPES.SNAKE_AND_LADDERS);
        assertEquals(tileManager.getSize(), tileManager.getSizeRaw());
        assertEquals(tileManager.getWidth(), 2);
        assertEquals(tileManager.getTiles().size(), 6);
    }

}
