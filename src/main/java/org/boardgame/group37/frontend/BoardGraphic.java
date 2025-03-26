package org.boardgame.group37.frontend;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BoardGraphic extends GridPane {
    private int heigth;
    private int width;
    private final int cellSize = 60;

    BoardGraphic(int heigth, int width) {
        this.heigth = heigth;
        this.width = width;

        this.setAlignment(Pos.CENTER);
        /* setting up the board */
        for (int row = 0; row < heigth; row++) {
            for (int col = 0; col < width; col++) {

                /* creating the tiles and coloring every other tile separate colours*/
                Rectangle tile = new Rectangle(cellSize, cellSize);
                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.BLUE);
                }
                else {
                    tile.setFill(Color.YELLOW);
                }

                /* adding the tile number to the tile */
                int tileNo = 0;
                /* making the bottom row go from left to right and then the second row alternate */
                if ((heigth - row - 1) % 2 == 0) {
                    tileNo = (heigth - row - 1) * width + col + 1;
                }
                else {
                    tileNo = (heigth - row) * width - col;
                }

                Text tileNoText = new Text(String.valueOf(tileNo));

                this.add(tile, col, row);
                this.add(tileNoText, col, row);
            }
        }
    }

    public void updatePlayerPosition(PlayerToken playertoken, int newPosition) {
        int row = (heigth*width - newPosition) / width;
        int col = 0;
        if ((heigth - row - 1) % 2 == 0) {
            col = width - 1 - ((heigth*width - newPosition) % width);
        }
        else {
            col = (heigth*width - newPosition) % width;
        }


        getChildren().remove(playertoken);
        add(playertoken, col, row);
    }
}
