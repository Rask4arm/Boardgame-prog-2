package org.boardgame.group37.backend.player;

import java.awt.Color;
import java.nio.file.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/*
 * PlayerDataManager class is responsible for saving and loading player data to and from files.
 * It uses the OpenCSV library to convert the data to and from CSV format.
 * The data is stored in the data/players.csv file.
 */
public class PlayerDataManager {

    public PlayerDataPackage dataLoad(String playerName) {
        try {
            CSVReader reader = new CSVReader(Files.newBufferedReader(Paths.get("data/players.csv")));
            String[] data;
            PlayerDataPackage dataReturn = null;
            while ((data = reader.readNext()) != null) {
                if (data[0].equals(playerName)) {
                    dataReturn = new PlayerDataPackage(data[0], Color.decode(data[1]), Integer.parseInt(data[2]));
                }
            }
            reader.close();
            return dataReturn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dataSave(PlayerDataPackage playerData) {
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

}
