package org.boardgame.group37.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.model.tile.Tile;
import org.boardgame.group37.model.tile.TileManager;
import org.boardgame.group37.model.tile.action.ActionMonopolyTile;
import org.boardgame.group37.model.tile.action.ActionSwitch;
import org.boardgame.group37.model.tile.action.ActionTeleport;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * BoardGraphic class
 * This class is responsible for creating the board graphic.
 */
public class BoardGraphic extends StackPane {

    private int heigth;
    private int width;
    private final int cellSize = 60;
    private TileManager tileManager;
    private final GridPane gridPane = new GridPane();
    ArrayList<Arrow> arrows = new ArrayList<>();


    /**
     * Creates a tile with a number on it
     * @param row row of the tile
     * @param col column of the tile
     */
    private void createTiles(int row, int col, ArrayList<Tile> tiles) {
        int tileIndex;
        // Calculate correct tile index
        if ((heigth - row - 1) % 2 == 0) {
            tileIndex = (heigth - row - 1) * width + col + 1;
        } else {
            tileIndex = (heigth - row) * width - col;
        }

        // Create rectangle tile with alternating colors
        if (tileIndex < heigth * width) {
            if (tiles.get(tileIndex).getAction() instanceof ActionTeleport) {
                createTile(ColorPalette.BOARD_TELEPORT_TILE, tileIndex);
            }
            else if (tiles.get(tileIndex).getAction() instanceof ActionMonopolyTile) {
                createTile(ColorPalette.BOARD_MONOPOLY_TILE, tileIndex);
            }
            else if (tiles.get(tileIndex).getAction() instanceof ActionSwitch) {
                createTile(ColorPalette.BOARD_TEXT , tileIndex);
            }
            else {
                if ((row + col) % 2 == 0) {
                    createTile(ColorPalette.BOARD_DARK_TILE, tileIndex);
                } else {
                    createTile(ColorPalette.BOARD_LIGHT_TILE, tileIndex);
                }
            }
        }
        else {
            if (tileManager.getBoardType() == BOARDTYPES.MONOPOLY) {
                createTile(ColorPalette.BOARD_MONOPOLY_TILE, tileIndex);
            }
            else if ((row + col) % 2 == 0) {
                createTile(ColorPalette.BOARD_DARK_TILE, tileIndex);
            } else {
                createTile(ColorPalette.BOARD_LIGHT_TILE, tileIndex);
            }
        }
    }

    /**
     * Creates ladders on the board
     * @param tiles tiles
     */
    private void createLadder(ArrayList<Tile> tiles){
        AtomicInteger tileIndex = new AtomicInteger();
        tiles.stream().filter(tile -> tile.getAction() instanceof ActionTeleport).forEach(tile -> {

            tileIndex.set(tiles.indexOf(tile));
            int row = (heigth*width - tileIndex.get()) / width;
            int col = 0;
            if ((heigth - row - 1) % 2 == 0) {
                col = width - 1 - ((heigth*width - tileIndex.get()) % width);
            } else {
                col = (heigth*width - tileIndex.get()) % width;
            }

            ActionTeleport actionTeleport = (ActionTeleport) ((ActionTeleport) tiles.get(tileIndex.get()).getAction());
            int targetIndex = actionTeleport.getTarget();
            gridPane.getChildren().remove(gridPane.lookup("#" + targetIndex));
            createTile(ColorPalette.UI_SUCCESS, targetIndex);

            // Calculate target row and column
            int targetRow = (heigth*width - targetIndex) / width;
            int targetCol = 0;
            if ((heigth - targetRow - 1) % 2 == 0) {
                targetCol = width - 1 - ((heigth*width - targetIndex) % width);
            } else {
                targetCol = (heigth*width - targetIndex) % width;
            }

            Arrow arrow = new Arrow();
            arrow.setStartX(50 + (col * (cellSize + 2)));
            arrow.setStartY(30 + (row * (cellSize + 2)));
            arrow.setEndX(50 + (targetCol * (cellSize + 2)));
            arrow.setEndY(30 + (targetRow * (cellSize + 2)));
            arrows.add(arrow);
        });
    }



    /**
     * Constructor for the BoardGraphic class

     * @param tileManager tileManager
     *
     * Creates a grid of tiles with alternating colors and numbers
     */
    public BoardGraphic(TileManager tileManager, BOARDTYPES boardType) {
        this.tileManager = tileManager;
        this.heigth = tileManager.getSize()/tileManager.getWidth();
        this.width = tileManager.getWidth();

        // Board setup
        ArrayList<Tile> tiles = tileManager.getTiles();

        if (boardType == BOARDTYPES.SNAKE_AND_LADDERS) {
            createSnakesAndLaddersBoard(tiles, heigth, width);
        } else if (boardType == BOARDTYPES.MONOPOLY) {
            createMonopolyBoard(tiles, heigth, width);
        }


        // Add spacing
        gridPane.setHgap(1);
        gridPane.setVgap(1);
    }

    /**
     * Updates the position of the player token on the board
     * @param playertoken player token
     * @param newPosition new position
     */
    public void updatePlayerPosition(PlayerToken playertoken, int newPosition) {
        
        // Calculate row
        int row = (heigth*width - newPosition) / width;

        // Calculate coloumn
        int col = 0;
        if ((heigth - row - 1) % 2 == 0) {
            col = width - 1 - ((heigth*width - newPosition) % width);
        } else {
            col = (heigth*width - newPosition) % width;
        }

        // Remove player token -> add player token to new position
        gridPane.getChildren().remove(playertoken);
        gridPane.add(playertoken, col, row);
    }

    /**
     * Returns the tile manager
     * @return tileManager
     * @see TileManager
     */
    public TileManager getTileManager() {
        return tileManager;
    }

    /**
     * Creates a tile with a number on it
     * @param color color of the tile
     * @param tileIndex index of the tile
     */
    public void createTile(Color color, int tileIndex) {

        Rectangle tile = new Rectangle(cellSize, cellSize);
        int row = (heigth*width - tileIndex) / width;
        int col = 0;
        if ((heigth - row - 1) % 2 == 0) {
            col = width - 1 - ((heigth*width - tileIndex) % width);
        } else {
            col = (heigth*width - tileIndex) % width;
        }

        // Calculate correct tile index
        if ((heigth - row - 1) % 2 == 0) {
            tileIndex = (heigth - row - 1) * width + col + 1;
        } else {
            tileIndex = (heigth - row) * width - col;
        }

        tile.setFill(color);

        // Set tile border and padding
        tile.setArcWidth(10);
        tile.setArcHeight(10);
        tile.setStroke(ColorPalette.BOARD_BORDER);
        tile.setStrokeWidth(1);

        // Create text
        Text tileText = new Text(String.valueOf(tileIndex));

        // Stack tile and text together (for alignment)
        StackPane stack = new StackPane();
        stack.getChildren().addAll(tile, tileText);
        stack.setAlignment(Pos.CENTER);
        stack.setId(String.valueOf(tileIndex));

        // Add tile to the board
        gridPane.add(stack, col, row);
    }

    /**
     * Creates a snakes and ladders board
     * @param tiles tiles
     * @param heigth height of the board
     * @param width width of the board
     */
    public void createSnakesAndLaddersBoard(ArrayList<Tile> tiles, int heigth, int width) {
        for (int row = 0; row < heigth; row++) {
            for (int col = 0; col < width; col++) {
                createTiles(row, col, tiles);
            }
        }
        createLadder(tiles);
        getChildren().add(gridPane);
        for (Arrow arrow : arrows) {
            setAlignment(arrow, Pos.TOP_LEFT);
            if (arrow.getStartX() < arrow.getEndX()) {
                if (arrow.getStartY() < arrow.getEndY()) {
                    setMargin(arrow, new Insets(arrow.getStartY() , 0, 0, arrow.getStartX()));
                } else {
                    setMargin(arrow, new Insets(arrow.getEndY() , 0, 0, arrow.getStartX()));
                }
            }
            else {
                if (arrow.getStartY() < arrow.getEndY()) {
                    setMargin(arrow, new Insets(arrow.getStartY() , 0, 0, arrow.getEndX()));
                } else {
                    setMargin(arrow, new Insets(arrow.getEndY() , 0, 0, arrow.getEndX()));
                }
            }
            getChildren().add(arrow);
        }
    }

    /**
     * Creates a monopoly board
     * @param tiles tiles
     * @param heigth height of the board
     * @param width width of the board
     */
    public void createMonopolyBoard(ArrayList<Tile> tiles, int heigth, int width) {
        for (int row = 0; row < heigth; row++) {
            for (int col = 0; col < width; col++) {
                createTiles(row, col, tiles);
            }
        }
        getChildren().add(gridPane);
    }

    /**
     * Gets the board type
     * @return board type
     * @see BOARDTYPES
     */
    public BOARDTYPES getBoardType() {
        return tileManager.getBoardType();
    }

}
