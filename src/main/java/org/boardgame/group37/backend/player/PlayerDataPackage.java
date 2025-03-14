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

    /*
     * Constructor
     * Initializes the PlayerDataPackage with the specified name, color, and number of wins.
     * @param name: Player name
     */
    PlayerDataPackage(String name, Color color, int wins) {
        this.name = name;
        this.color = color;
        this.wins = wins;
    }

    // Getters

    /*
     * getName method returns the player's name.
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /*
     * getColor method returns the player's color.
     * @return player's color
     */
    public Color getColor() {
        return color;
    }

    /*
     * getWins method returns the number of wins.
     * @return number of wins
     */
    public int getWins() {
        return wins;
    }
    
}
