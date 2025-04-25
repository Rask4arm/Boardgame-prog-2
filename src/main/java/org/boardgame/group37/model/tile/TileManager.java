package org.boardgame.group37.model.tile;
import java.util.ArrayList;

import org.boardgame.group37.model.tile.action.*;

/**
 * TileManager class is responsible for storing and generating tiles.
 * It is used to store and generate tiles for the board.
 */
public class TileManager {

    // Properties
    private int width = 0;
    private int size = 0;
    private ArrayList<Tile> tiles = new ArrayList<>();

    /**
     * Initializes the TileManager with an empty ArrayList of tiles.
     */
    public TileManager() {
        System.out.println("Warning: empty TileManager created.");
    }

    /**
     * Initializes the TileManager with the specified width and size.
     * @param width: width of the board
     * @param size: number of tiles
     * Generates tiles based on the specified size.
     */
    public TileManager(int width, int size) {
        System.out.println(String.format("Debug: TileManager created with width: %d, size: %d", width, size));
        this.width = width;
        this.size = size;
        tilesGenerate();
    }

    /**
     * Initializes the TileManager with the specified ArrayList of Tile objects.
     * @param tiles
     */
    public TileManager(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    // Methods

    /**
     * tileAdd method adds a new Tile object to the ArrayList.
     * @param tile: Tile object
     */
    public void tileAdd(Tile tile) {
        tiles.add(tile);
        System.out.println(String.format("\nDebug: Tile added. Current number of tiles: %d", tiles.size()));
    }

    /**
     * tilesGenerate method generates tiles based on the specified size.
     * Tiles are generated with random properties.
     */
    public void tilesGenerate() {
        System.out.println("Debug: Generating tiles.");
        for(int i = 0; i < size; i++) {

            // Initialize random properties
            double rand = Math.random();
            Action action = null;

            // Set action based on random properties
            if (rand < .1) action = new ActionTeleport(i);
            else action = new ActionDefault();

            // Add tile to tiles
            tileAdd(new Tile(action));
        }
        System.out.println(String.format("Debug: Tiles generated successfully. Number of tiles: %d", tiles.size()));
    }

    /**
     * getTiles method returns the ArrayList of Tile objects.
     * @return ArrayList of Tile objects
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    /**
     * getWidth method returns the width of the board.
     * @return width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * getSize method returns the number of tiles it is supposed to generate.
     * @return number of tiles
     */
    public int getSize() {
        return size;
    }

    /**
     * getSizeRaw method returns the number of tiles in the ArrayList.
     * @return number of tiles
     */
    public int getSizeRaw() {
        return tiles.size();
    }

    /**
     * toString method returns the number of tiles.
     * @return number of tiles
     */
    @Override
    public String toString() {
        return String.format("TileManager | %d Tiles", size);
    }
}
