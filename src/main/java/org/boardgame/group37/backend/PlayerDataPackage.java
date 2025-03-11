package org.boardgame.group37.backend;
import java.awt.Color;

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
