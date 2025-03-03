package org.boardgame.group37.backend;

public class Tile {
    private Action action;
    public Tile() {
        this.action = new ActionDefault();
    }
    public Tile(Action action) {
        this.action = action;
    }
}
