package org.boardgame.group37.backend.tile;

import java.util.ArrayList;

/*
 * TileDataPackage stores an ArrayList of Tile objects.
 * It is used to pass tile data between classes in filehandling.
 */
public class TileDataPackage {

    // Properties
    private ArrayList<Tile> tiles;

    // Constructor
    public TileDataPackage(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    // Getters
    public ArrayList<Tile> getTiles() {
        return tiles;
    }


}
