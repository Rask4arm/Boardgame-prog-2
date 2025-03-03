package org.boardgame.group37.backend;
import java.awt.Color;

public class Player {

    // Properties
    private Color color = Color.BLACK;
    private String name = "noname";
    private int position = 0;

    // Constructor
    public Player() {

    }
    public Player(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    // Methods
    public void executeMove() {
        position ++;
    }
    public void executeTile(TileManager TM, int tileIndex) {
        
    }
}
