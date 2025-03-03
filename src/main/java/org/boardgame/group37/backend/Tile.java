package org.boardgame.group37.backend;

public class Tile {

    // Properties
    private Action action;

    // Constructor
    public Tile() {
        this.action = new ActionDefault();
    }
    public Tile(Action action) {
        this.action = action;
    }

    // Methods
}
