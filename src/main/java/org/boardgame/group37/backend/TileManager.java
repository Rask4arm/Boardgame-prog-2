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
        this.width = width;
        this.size = size;
        tilesGenerate();
        System.out.println(String.format("Debug: TileManager created with width: %d, size: %d", width, size));
    }

    // Methods
    public void tileAdd(Tile tile) {
        tiles.add(tile);
        System.out.println(String.format("Debug: Tile added. Current number of tiles: %d", tiles.size()));
    }
    public void tilesGenerate() {
        for(int i = 0; i < size; i++) {
            tileAdd(new Tile());
        }
        System.out.println(String.format("Debug: Tiles generated. Number of tiles: %d", tiles.size()));
    }
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
