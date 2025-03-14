package org.boardgame.group37.backend.player;
import java.awt.Color;

/*
 * PlayerDataPackage class is responsible for storing player data.
 * It stores the player's name, color, and number of wins.
 * It is used to pass player data between classes.
 */
public class PlayerDataPackage {

    // Properties
    private String name;
    private Color color;
    private int wins;

    // Constructor
    PlayerDataPackage(String name, Color color, int wins) {
        this.name = name;
        this.color = color;
        this.wins = wins;
    }

    // Getters
    public String getName() {
        return name;
    }
    public Color getColor() {
        return color;
    }
    public int getWins() {
        return wins;
    }
    
}
