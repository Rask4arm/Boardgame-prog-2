package org.boardgame.group37.backend;

import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;


public class PlayerData {

    private void dataLoad() {
        
        String fileName = "data.csv";
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName));
        } catch (IOException e) {
            System.out.println("Error: File not found, creating new file");
        } catch (Exception e) {
            System.out.println("Error");
        } 

        // if file does not exist
        
    }
    
}
