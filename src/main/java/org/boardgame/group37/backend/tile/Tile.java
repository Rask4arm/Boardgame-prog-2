package org.boardgame.group37.backend.tile;

import org.boardgame.group37.backend.tile.action.*;

public class Tile {

    // Properties
    protected Action action; // The action that will be executed when a player lands on this tile

    // Constructor
    public Tile() {
        this.action = new ActionDefault();
        System.out.println(String.format("Debug: Tile created with action: %s", action.toString()));
    }

    public Tile(Action action) {
        this.action = action;
        System.out.println(String.format("Debug: Tile created with action: %s", action.toString()));
    }

    // Methods
    public Action getAction() {
        return action;
    }
}
