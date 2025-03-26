package org.boardgame.group37.player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.boardgame.group37.backend.player.Player;
import org.boardgame.group37.backend.tile.Tile;
import org.boardgame.group37.backend.tile.action.*;

import java.awt.Color;

public class TestPlayer {

    @Test
    public void testEmptyPlayer() {
        Player player = new Player();
        assertEquals(player.getColor(), Color.BLACK);
        assertEquals(player.getName(), "noname");
    }

    @Test void testPredefinedPlayer() {
        Player player = new Player("test", Color.RED);
        assertEquals(player.getColor(), Color.RED);
        assertEquals(player.getName(), "test");
    }

    @Test void testPlayerGeneral() {

        // Test executeMove method
        Player player = new Player();
        assertEquals(player.getPosition(), 0);
        player.executeMove();
        assertEquals(player.getPosition(), 1);

        // Test executeTile method
        Tile tile1 = new Tile(new ActionDefault());
        Tile tile2 = new Tile(new ActionTeleport(5));

        player = new Player();
        player.executeTile(tile1);
        assertEquals(player.getPosition(), 1);
        player.executeTile(tile2);
        assertEquals(player.getPosition(), 5);
    }

}
