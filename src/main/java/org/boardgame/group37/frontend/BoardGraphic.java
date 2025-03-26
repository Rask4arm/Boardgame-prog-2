package org.boardgame.group37.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BoardGraphic extends GridPane {
    private int heigth;
    private int width;
    private final int cellSize = 60;

    /**
     * Creates a tile with a number on it
     * @param row row of the tile
     * @param col column of the tile
     */
    private void createTile(int row, int col, int tileIndex) {

        // Create rectangle tile with alternating colors
        Rectangle tile = new Rectangle(cellSize, cellSize);
        if ((row + col) % 2 == 0) {
            tile.setFill(ColorPalette.BOARD_DARK_TILE);
        } else {
            tile.setFill(ColorPalette.BOARD_LIGHT_TILE);
        }

        // Calculate correct tile index
        if ((heigth - row - 1) % 2 == 0) {
            tileIndex = (heigth - row - 1) * width + col + 1;
        } else {
            tileIndex = (heigth - row) * width - col;
        }

        // Create text
        Text tileText = new Text(String.valueOf(tileIndex));

        // Stack tile and text together (for alignment)
        StackPane stack = new StackPane();
        stack.getChildren().addAll(tile, tileText);
        stack.setAlignment(Pos.CENTER);
        
        // Add tile to the board
        add(stack, col, row);
    }

    /**
     * Constructor for the BoardGraphic class
     * @param heigth height
     * @param width width
     */
    BoardGraphic(int heigth, int width) {
        this.heigth = heigth;
        this.width = width;

        this.setAlignment(Pos.CENTER);
        
        // Board setup
        int tileIndex = 0;
        for (int row = 0; row < heigth; row++) {
            for (int col = 0; col < width; col++) {
                tileIndex++;
                createTile(row, col, tileIndex);
            }
        }
    }

    /**
     * Updates the position of the player token on the board
     * @param playertoken player token
     * @param newPosition new position
     */
    public void updatePlayerPosition(PlayerToken playertoken, int newPosition) {
        int row = (heigth*width - newPosition) / width;
        int col = 0;
        if ((heigth - row - 1) % 2 == 0) {
            col = width - 1 - ((heigth*width - newPosition) % width);
        } else {
            col = (heigth*width - newPosition) % width;
        }


        getChildren().remove(playertoken);
        add(playertoken, col, row);
    }
}
