package org.boardgame.group37.backend;
import java.util.ArrayList;

public class TileManager {

    private int width = 0;
    private int size = 0;

    // Properties
    private ArrayList<Tile> tiles = new ArrayList<>();

    // Constructor
    TileManager() {
        System.out.println("Debug: empty TileManager created.");
    }
    TileManager(int width, int size) {
        System.out.println(String.format("Debug: TileManager created with width: %d, size: %d", width, size));
        this.width = width;
        this.size = size;
        tilesGenerate();
    }

    // Methods
    public void tileAdd(Tile tile) {
        tiles.add(tile);
        System.out.println(String.format("\nDebug: Tile added. Current number of tiles: %d", tiles.size()));
    }
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
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
    public int getWidth() {
        return width;
    }
    public int getSize() {
        return size;
    }
}
