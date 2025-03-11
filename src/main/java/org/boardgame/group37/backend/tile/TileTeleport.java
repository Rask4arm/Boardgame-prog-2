package org.boardgame.group37.backend.tile;

import org.boardgame.group37.backend.tile.action.*;

public class TileTeleport extends Tile {

    public TileTeleport(int targetIndex) {
        super(new ActionTeleport(targetIndex));
    }
}
