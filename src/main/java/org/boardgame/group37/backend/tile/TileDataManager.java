package org.boardgame.group37.backend.tile;

import com.google.gson.Gson;                // JSON library
import com.google.gson.reflect.TypeToken;   // JSON get type
import java.util.ArrayList;
import java.nio.file.*;
import java.lang.reflect.Type;

public class TileDataManager {

    public void dataSave(ArrayList<Tile> tiles, String fileName) {

        // Save data to file
        try {
            Gson gson = new Gson();
            String json = gson.toJson(tiles);
            Files.write(java.nio.file.Paths.get("data/" + fileName), json.getBytes());

        // Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public ArrayList<Tile> dataLoad(String fileName) {

        // Load data from file
        try {
            Gson gson = new Gson();
            String json = Files.readString(java.nio.file.Paths.get("data/" + fileName));
            Type type = new TypeToken<ArrayList<Tile>>(){}.getType();   // Gets the type stored in the json file
            ArrayList<Tile> tiles = gson.fromJson(json, type);
            return tiles;

        // Catch no file exception
        } catch (NoSuchFileException e) {
            System.out.println("File not found: " + fileName);
            return null;

        // Catch other exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
