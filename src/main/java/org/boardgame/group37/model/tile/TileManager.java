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
    private BOARDTYPES boardType;

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
    public TileManager(int width, int size, BOARDTYPES type) throws Exception {
        System.out.println(String.format("Debug: TileManager created with width: %d, size: %d, board type:", width, size) + type);
        this.width = width;
        this.size = size;
        this.boardType = type;
        this.tilesGenerate(type);
    }

    /**
     * Initializes the TileManager with the specified ArrayList of Tile objects.
     * @param tileManager: TileManager object
     */
    public TileManager(TileManager tileManager, BOARDTYPES type) throws Exception {
        this.tiles = tileManager.getTiles();
        this.width = tileManager.getWidth();
        this.size = tileManager.getSize();
        this.boardType = type;
        this.tilesGenerate(type);
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
    public void tilesGenerate(BOARDTYPES type) throws Exception {

        // Check if tiles are already generated
        if (tiles.size() > 0) {
            throw new IllegalArgumentException("Tiles already generated. Please create a new TileManager object to generate new tiles.");
        }

        if (size <= 2) {
            throw new IllegalArgumentException("Size must be greater than 5.");
        }

        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than 0.");
        }

        switch(type) {
            case SNAKE_AND_LADDERS -> {
                System.out.println("Debug: Generating tiles.");
                for(int i = 0; i < size; i++) {

                    // Initialize random properties
                    double rand = Math.random();
                    Action action = null;

                    // Set action based on random properties
                    if (i == 0 || i == tiles.size() - 1) {
                        action = new ActionDefault();
                    } else if (rand < .2)  {
                        int teleportIndex = Math.clamp(i + (int)(Math.random() * 25 - 12), 1, size - 2);
                        action = new ActionTeleport(teleportIndex);
                    } else if (rand < .3) {
                        action = new ActionSwitch();
                    } else { 
                        action = new ActionDefault();
                    }

                    tileAdd(new Tile(action));
                }
                System.out.println(String.format("Debug: Tiles generated successfully. Number of tiles: %d", tiles.size()));
            }
            case MONOPOLY -> {
                System.out.println("Debug: Generating tiles.");
                tileAdd(new Tile(new ActionMonopolyStart()));
                for(int i = 1; i < size; i++) {

                    // Add tile to tiles
                    tileAdd(new Tile(new ActionMonopolyTile((i+4) * 20)));
                }
                System.out.println(String.format("Debug: Tiles generated successfully. Number of tiles: %d", tiles.size()));
            }
        }
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

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    /**
     * getBoardType method returns the type of the board.
     * @return board type
     */
    public BOARDTYPES getBoardType() {
        return boardType;
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
