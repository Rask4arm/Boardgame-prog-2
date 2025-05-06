package org.boardgame.group37.model.player;

import javafx.scene.paint.Color;

import org.boardgame.group37.model.tile.Tile;
import org.boardgame.group37.model.tile.TileManager;

/**
 * Player class is responsible for managing player data.
 * It stores the player's color, name, and position on the board.
 */
public class Player {

    // Properties
    private Color color = Color.BLACK;
    private String name = "noname";
    private int position = 0;
    private int money = 1000;

    /**
     * Constructor
     * Initializes the Player with default values.
     */
    public Player() {
        System.out.println("Debug: Empty player created");
    }

    /**
     * Constructor
     * Initializes the Player with the specified color and name.
     * @param color: Player color
     * @param name: Player name
     */
    public Player(String name, Color color) {
        this.color = color;
        this.name = name;
        System.out.println(String.format("Debug: Player with Color: %s, Name: %s created", color.toString(), name));
    }

    // Methods

    /**
     * executeMove method moves the player one step forward.
     * This method is used when NOT activating a tile. (with rolls left from the die)
     */
    public void executeMove() {
        position ++;
    }

    /**
     * executeTile method executes the action of the tile the player is on. (when no rolls left from the die)
     * @param TM: TileManager object
     * @param tileIndex: index of the tile the player is on
     */
    public void executeTile(TileManager TM, int tileIndex) throws Exception {
        TM.getTiles().get(tileIndex).getAction().execute(this);
    }

    /**
     * executeTile method executes the action of the tile the player is on. (when no rolls left from the die)
     * @param tile: Tile object
     */
    public void executeTile(Tile tile) throws Exception{
        tile.getAction().execute(this);
    }

    // Getters & Setters

    /**
     * getPosition method returns the player's position on the board.
     * @return player's position on the board
     */
    public int getPosition() {
        return position;
    }

    /**
     * getColor method returns the player's color.
     * @return player's color in Color object
     */
    public Color getColor() {
        return color;
    }

    /**
     * getName method returns the player's name.
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * resetPosition method resets the player's position to 0.
     */
    public void resetPosition() {
        position = 0;
        System.out.println(String.format("Debug: Player %s reset position to %d", name, position));
    }
    /**
     * setColor method sets the player's color.
     * @param color: player's color
     */
    public void setColor(Color color) {
        this.color = color;
        System.out.println(String.format("Debug: Player %s set color to %s", name, color.toString()));
    }

    /**
     * setName method sets the player's name.
     * @param name: player's name
     */
    public void setName(String name) {
        this.name = name;
        System.out.println(String.format("Debug: Player %s set name to %s", name, name));
    }

    
    /**
     * setPosition method sets the player's position on the board.
     * @param position: player's position on the board
     */
    public void setPosition(int position) {
        this.position = position;
        System.out.println(String.format("Debug: Player %s set position to %d", name, position));
    }

    /**
     * toString method returns the player's name and color, and position as a string.
     * @return player's name, color, and position as a string
     */
    @Override
    public String toString() {
        return String.format("Player | Name: %s, Color: %s, Position: %d", name, color.toString(), position);
    }

    /**
     * getMoney method returns the player's money.
     * @return player's money
     */
    public int getMoney() {
        return money;
    }

    /**
     * setMoney method sets the player's money.
     * @param money: player's money
     */
    public void setMoney(int money) {
        this.money = money;
        System.out.println(String.format("Debug: Player %s set money to %d", name, money));
    }
}
