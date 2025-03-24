package org.boardgame.group37.backend.tile;

import org.boardgame.group37.backend.tile.action.*;

/**
 * TileTeleport class is responsible for storing the action of the teleport tile.
 */
public class TileTeleport extends Tile {

    /**
     * Initializes the TileTeleport with the specified target index.
     * @param targetIndex: int
     */
    public TileTeleport(int targetIndex) {
        super(new ActionTeleport(targetIndex));
    }
}
