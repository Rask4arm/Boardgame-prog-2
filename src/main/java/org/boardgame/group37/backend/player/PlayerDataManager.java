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

    /**
     * dataLoad method loads the player data from the file.
     * @param playerName: Name of the player to load
     * @return: PlayerDataPackage object loaded from the file OR null
     */
    public static Player dataLoad(String playerName) {
    
        // Fetch and return the player data
        try {

            // Initialize values
            Path path = Paths.get("data/players.csv");
            CSVReader reader = new CSVReader(Files.newBufferedReader(path));
            String[] data;
            Player dataReturn = null;

            // Fetch the player data
            while ((data = reader.readNext()) != null) {
                if (data[0].equals(playerName)) {
                    dataReturn = new Player(Color.decode(data[1]), data[0]);
                    break;
                }
            }

            // Close the reader and return the data
            reader.close();
            return new Player(dataReturn.getColor(), dataReturn.getName());

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
    public static ArrayList<Player> dataLoad(){
        try {
            
            // Initialize values
            Path path = Paths.get("data/players.csv");
            CSVReader reader = new CSVReader(Files.newBufferedReader(path));
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
     * dataSave method saves the player data to the file.
     * @param playerData: PlayerDataPackage object to save
     */
    public static void dataSave(PlayerDataPackage playerData) {
        try {
            CSVWriter writer = new CSVWriter(Files.newBufferedWriter(Paths.get("data/players.csv"), StandardOpenOption.APPEND));
            String[] data = {playerData.getName(), Integer.toString(playerData.getColor().hashCode()), Integer.toString(playerData.getWins())};
            writer.writeNext(data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

    public static void dataUpdate(PlayerDataPackage playerData) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dataDelete(String playerName) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dataDelete(){};

}
