package org.boardgame.group37.backend.tile;

import org.boardgame.group37.backend.tile.action.*;

/*
 * Tile class is responsible for storing the action of the tile.
 */
public class Tile {

    // Properties
    protected Action action; // The action that will be executed when a player lands on this tile

    // Constructor

    /*
     * Constructor
     * Initializes the Tile with the default action.
     */
    public Tile() {
        this.action = new ActionDefault();
        System.out.println(String.format("Debug: Tile created with action: %s", action.toString()));
    }

    /*
     * Constructor
     * Initializes the Tile with the specified action.
     * @param action: Action object
     */
    public Tile(Action action) {
        this.action = action;
        System.out.println(String.format("Debug: Tile created with action: %s", action.toString()));
    }

    // Methods

    /*
     * getAction method returns the action of the tile.
     * @return action of the tile
     */
    public Action getAction() {
        return action;
    }

    /*
     * toString method returns the name of the action.
     * @return name of the action
     */
    @Override
    public String toString() {
        return String.format("Tile | Action: %s", action.toString());
    }
}
