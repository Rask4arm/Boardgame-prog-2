package org.boardgame.group37.model.tile;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.nio.file.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;

import org.boardgame.group37.model.tile.action.*;

/**
 * TileDataManager class is responsible for saving and loading tile data to and from files.
 * It uses the Gson library to convert the data to and from JSON format.
 * The data is stored in the data/board directory.
 */
public class TileDataManager {

    /**
     * createGson method creates a Gson object with custom adapters for action and tile types.
     * @return: Gson object with custom adapters
     */
    private static Gson createGson() {

        // Create adapter for different types of actions
        RuntimeTypeAdapterFactory<Action> actionAdapter =
            RuntimeTypeAdapterFactory.of(Action.class, "type")
                .registerSubtype(ActionDefault.class, "default")
                .registerSubtype(ActionTeleport.class, "teleport")
                .registerSubtype(ActionMonopolyStart.class, "monopolyStart")
                .registerSubtype(ActionMonopolyTile.class, "monopolyTile")
                .registerSubtype(ActionSwitch.class, "switch");

        // Create a new GsonBuilder instance
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Register adapters to the Gson object
        gsonBuilder.registerTypeAdapterFactory(actionAdapter);

        // Pretty print the JSON for debugging and readability
        gsonBuilder.setPrettyPrinting();
        return gsonBuilder.create();
        
    }

    /**
     * dataInit method creates missing directories.
     */
    public static final void dataInit() {
        // Get the path
        Path path = java.nio.file.Paths.get("data/board");

        // Create the directory if it does not exist
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * dataSave method saves the tile data to a file.

     * @param tileManager: TileManager to save
     * @param fileName: Name of the file to save to
     */
    public static final void dataSave(TileManager tileManager, String fileName) {


        // Save data to file
        dataInit(); // Initialize the data directory
        try {
            Gson gson = createGson();
            String json = gson.toJson(tileManager);

            String fullFileName = fileName.endsWith(".json") ? fileName : fileName + ".json";
            Files.write(java.nio.file.Paths.get("data/board/" + fullFileName), json.getBytes());
            
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
    public static TileManager dataLoad(String fileName) throws Exception {

        // Load data from file
        try {

            // Initialize file
            dataInit();

            // Json to object initialization
            Gson gson = createGson();

            // Get file path
            String fullFileName = fileName.endsWith(".json") ? fileName : fileName + ".json";
            Path path = java.nio.file.Paths.get("data/board/" + fullFileName);


            // Read file
            String json = Files.readString(path);

            // Convert json to object
            Type type = new TypeToken<TileManager>(){}.getType();   // Gets the type stored in the json file
            
            // Return the object
            TileManager tilemanager = gson.fromJson(json, type);
            return tilemanager;

        // Catch no file exception
        } catch (NoSuchFileException e) {
            throw new FileNotFoundException("File not found: " + fileName);

        // Catch other exceptions
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error loading file: " + fileName + "\n" + e.getMessage());
        }
    }

    /**
     * dataGetFilenames method gets all the filenames in the data/board directory.
     * @return: String array of all filenames in the data/board directory OR null
     */
    public static final String[] dataGetFilenames() {

        // Get all filenames in the data/board directory
        try {

            // Initialize file
            dataInit();

            // Get path
            Path path = java.nio.file.Paths.get("data/board");

            // Path to string array
            String[] filenames = Files.list(path)
                .map(_path -> _path.getFileName())
                .map(_path -> _path.toString())
                .filter(name -> name.endsWith(".json")) // Filter out non-json files just in case
                .toArray(size -> new String[size]);
            return filenames;

        // Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static final boolean dataDelete(String fileName) throws Exception {
        try {
            // Initialize file
            dataInit();
    
            // Get path
            String fullFileName = fileName.endsWith(".json") ? fileName : fileName + ".json";
            Path path = java.nio.file.Paths.get("data/board/" + fullFileName);
    
            // Check if the file exists
            if (Files.exists(path)) {
                // Delete the file
                Files.delete(path);
                System.out.println("Debug: File deleted successfully: " + fullFileName);
                return true; // Indicate success
            } else {
                throw new FileNotFoundException("File not found: " + fullFileName);
            }
        } catch (Exception e) {
            System.err.println("Error deleting file: " + fileName);
            e.printStackTrace();
            return false; // Indicate failure
        }
    }

    /**
     * dataDeleteAll method deletes all files in the data/board directory.
     * @return: boolean indicating success or failure
     */

    public static final boolean dataDeleteAll() throws Exception {
        String[] filenames = dataGetFilenames();
        if (filenames == null) {
            System.out.println("Debug: No files to delete.");
            return false;
        }
        else {
            System.out.println("Debug: Files to delete: " + filenames.length);
            for (String filename : filenames) {
                try {
                    dataDelete(filename);
                } catch (Exception e) {
                    System.err.println("Error deleting file: " + filename);
                    e.printStackTrace();
                    return false; // Indicate failure
                }
            }
            return true; // Indicate success
        }
    }
}
