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
        for (int row = 0; row < heigth; row++) {
            for (int col = 0; col < width; col++) {

                Rectangle tile = new Rectangle(cellSize, cellSize);
                if ((row + col) % 2 == 0) {
                    tile.setFill(Color.BLUE);
                }
                else {
                    tile.setFill(Color.YELLOW);
                }

                int tileNo = 0;
                if ((heigth - row - 1) % 2 == 0) {
                    tileNo = (heigth - row - 1) * width + col + 1;
                }
                else {
                    tileNo = ((heigth - row - 1) + 1) * width - (col);
                }

                Text tileNoText = new Text(String.valueOf(tileNo));

                this.add(tile, col, row);
                this.add(tileNoText, col, row);
            }
        }
    }

    public void updatePlayerPosition(PlayerToken playertoken, int newPosition) {
        int row = (heigth*width - newPosition) / width;
        int col = (heigth*width) % width;

        getChildren().remove(playertoken);
        add(playertoken, col, row);
    }
}
