package org.boardgame.group37.backend.player;

import java.awt.Color;
import java.nio.file.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.util.ArrayList;

/**
 * PlayerDataManager class is responsible for saving and loading player data to and from files.
 * It uses the OpenCSV library to convert the data to and from CSV format.
 * The data is stored in the data/players.csv file.
 */
public class PlayerDataManager {

    //#region Private properties
    
    private static final Path path = Paths.get("data/players.csv");
    
    /**
     * returns the path of the data/players.csv file.
     * @return Path object
     */
    private static final Path getPath() {
        return path;
    }

    /**
     * dataInit method creates missing directories and files.
     */
    private static final void dataInit() {
        // Get the path
        Path path = getPath();

        // Create the file if it does not exist
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //#endregion
    //#region Public methods

    /**
     * dataLoad method loads the player data from the file.
     * @param playerName: Name of the player to load
     * @return: PlayerDataPackage object loaded from the file OR null
     */
    public static Player dataLoad(String playerName) {
    
        // Fetch and return the player data
        try {

            // Initialize file
            dataInit();

            // Initialize values
            Path path = getPath();
            CSVReader reader = new CSVReader(Files.newBufferedReader(path));
            String[] data;
            Player dataReturn = null;

            // Fetch the player data
            while ((data = reader.readNext()) != null) {
                if (data[0].equals(playerName)) {
                    dataReturn = new Player(data[0], Color.decode(data[1]));
                    break;
                }
            }

            // Close the reader and return the data
            reader.close();
            return new Player(dataReturn.getName(), dataReturn.getColor());

        // Catch exceptions
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * dataLoad method loads all player data from the file.
     * @return: ArrayList of Player objects loaded from the file OR null
     */
    public static final ArrayList<Player> dataLoad(){
        try {
            
            // Initialize file
            dataInit();

            // Initialize values
            CSVReader reader = new CSVReader(Files.newBufferedReader(getPath()));
            String[] data;
            ArrayList<Player> dataReturn = new ArrayList<>();

            // Fetch the player data
            while ((data = reader.readNext()) != null) {
                dataReturn.add(new Player(Color.decode(data[1]), data[0]));
            }

            // Close the reader and return the data
            reader.close();
            return dataReturn;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * dataSave method saves a new player data to the file.
     * @param playerData: PlayerDataPackage object to save
     */
    public static final void dataSave(PlayerDataPackage playerData) {
        try {

            // Initialize file
            dataInit();

            // Write the data on a new line in the file
            CSVWriter writer = new CSVWriter(Files.newBufferedWriter(getPath(), StandardOpenOption.APPEND));
            String[] data = {playerData.getName(), Integer.toString(playerData.getColor().hashCode())};
            writer.writeNext(data);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dataDelete method deletes the player data from the file.
     * @param playerName Name of the player to delete
     */
    public static final void dataDelete(String playerName) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void dataDeleteAll(){
        try {
            Files.delete(getPath());
            dataInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

}
