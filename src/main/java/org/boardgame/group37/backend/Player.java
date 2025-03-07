package org.boardgame.group37.backend;
import java.awt.Color;

public class Player {

    // Properties
    private Color color = Color.BLACK;
    private String name = "noname";
    private int position = 0;

    // Constructor
    public Player() {
        System.out.println("Debug: Empty player created");
    }
    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
        System.out.println(String.format("Debug: Player with Color: %s, Name: %s created", color.toString(), name));
    }

    // Methods
    public void executeMove() {
        position ++;
    }
    public void executeTile(TileManager TM, int tileIndex) {
        TM.getTiles().get(tileIndex).getAction().execute(this);
    }
    public void executeTile(Tile tile) {
        tile.getAction().execute(this);
    }
    public int getPosition() {
        return position;
    }
    public Color getColor() {
        return color;
    }
    public String getName() {
        return name;
    }
    public void resetPosition() {
        position = 0;
        System.out.println(String.format("Debug: Player %s reset position to %d", name, position));
    }
    public void setPosition(int position) {
        this.position = position;
        System.out.println(String.format("Debug: Player %s set position to %d", name, position));
    }
}
