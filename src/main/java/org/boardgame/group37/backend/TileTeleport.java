package org.boardgame.group37.backend;

public class TileTeleport extends Tile {

    public TileTeleport(int targetIndex) {
        super(new ActionTeleport(targetIndex));
    }
}
