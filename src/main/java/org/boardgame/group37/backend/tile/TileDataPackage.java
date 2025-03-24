package org.boardgame.group37.backend.tile;

import java.util.ArrayList;

/**
 * TileDataPackage stores an ArrayList of Tile objects.
 * It is used to pass tile data between classes in filehandling.
 */
public class TileDataPackage {

    // Properties
    private ArrayList<Tile> tiles;

    /**
     * Initializes the TileDataPackage with the specified ArrayList of Tile objects.
     * @param tiles: ArrayList of Tile objects
     */
    public TileDataPackage(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }
    
    // Getters

    /**
     * getTiles method returns the ArrayList of Tile objects.
     * @return ArrayList of Tile objects
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }


}
