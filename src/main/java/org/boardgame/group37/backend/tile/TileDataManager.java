package org.boardgame.group37.backend.tile;

import com.google.gson.Gson;                // JSON library
import com.google.gson.reflect.TypeToken;   // JSON get type
import java.util.ArrayList;
import java.nio.file.*;
import java.lang.reflect.Type;

/**
 * TileDataManager class is responsible for saving and loading tile data to and from files.
 * It uses the Gson library to convert the data to and from JSON format.
 * The data is stored in the data/board directory.
 */
public class TileDataManager {

    /**
     * dataSave method saves the tile data to a file.
     * @param tiles: ArrayList of Tile objects to save
     * @param fileName: Name of the file to save to
     */
    public void dataSave(ArrayList<Tile> tiles, String fileName) {

        // Save data to file
        try {
            Gson gson = new Gson();
            String json = gson.toJson(tiles);
            Files.write(java.nio.file.Paths.get("data/board/" + fileName), json.getBytes());

        // Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    /**
     * dataLoad method loads the tile data from a file.
     * @param fileName: Name of the file to load from
     * @return: ArrayList of Tile objects loaded from the file OR null
     */
    public ArrayList<Tile> dataLoad(String fileName) throws Exception {

        // Load data from file
        try {
            Gson gson = new Gson();
            String json = Files.readString(java.nio.file.Paths.get("data/board/" + fileName));
            Type type = new TypeToken<ArrayList<Tile>>(){}.getType();   // Gets the type stored in the json file
            ArrayList<Tile> tiles = gson.fromJson(json, type);
            return tiles;

        // Catch no file exception
        } catch (NoSuchFileException e) {
            throw new Exception("File not found: " + fileName);

        // Catch other exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * dataGetFilenames method gets all the filenames in the data/board directory.
     * @return: String array of all filenames in the data/board directory OR null
     */
    public String[] dataGetFilenames() {

        // Get all filenames in the data/board directory
        try {
            String[] filenames = Files.list(java.nio.file.Paths.get("data/board"))
                .map(path -> path.getFileName())
                .map(path -> path.toString())
                .filter(name -> name.endsWith(".json")) // Filter out non-json files just in case
                .toArray(size -> new String[size]);
            return filenames;

        // Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
