package org.boardgame.group37.frontend;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.boardgame.group37.backend.game.GameManager;
import org.boardgame.group37.backend.tile.Tile;
import org.boardgame.group37.backend.tile.TileDataManager;
import org.boardgame.group37.backend.tile.TileManager;

public class Game {
    public static void init(Pane root, BoardGraphic boardGraphic){
        root.getChildren().clear();
        PlayerToken[] playerToken = new PlayerToken[2];
        GameManager gameManager;
        playerToken[0] = new PlayerToken(Color.RED);
        playerToken[1] = new PlayerToken(Color.ORANGE);
        gameManager = new GameManager(boardGraphic.getColumnCount(), boardGraphic.getColumnCount() * boardGraphic.getRowCount());

        gameManager.getPlayerManager().playerAdd();
        gameManager.getPlayerManager().playerAdd();
        gameManager.getDieManager().dieAdd();
        gameManager.getDieManager().dieAdd();

        gameManager.printProperties();

        gameManager.gameStart();
        Button diceButton = new Button("Roll dice?");

        diceButton.setOnAction(e ->
                {gameManager.roundDie();
                    while (gameManager.getCurrentPlayerRolls() > 0) {
                        if (gameManager.getState().equals("end")) break;
                        gameManager.roundMove();
                        boardGraphic.updatePlayerPosition(playerToken[gameManager.getCurrentPlayerIndex()], gameManager.getCurrentPlayerPosition());
                    }
                    gameManager.roundEnd();
                }
        );

        boardGraphic.updatePlayerPosition(playerToken[0], 1);
        boardGraphic.updatePlayerPosition(playerToken[1], 1);

        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().addAll(boardGraphic, diceButton);



        // new thing
        VBox root1 = new VBox();
        root1.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        Label l = new Label("Welcome to our snakes and ladders game");


        TileManager tileManager = new TileManager();
        tileManager.tileAdd(new Tile());
        tileManager.tileAdd(new Tile());

        TileManager tileManager2 = new TileManager();
        tileManager2.tileAdd(new Tile());
        tileManager2.tileAdd(new Tile());

        // Save and load board data
        TileDataManager.dataSave(tileManager.getTiles(), "test_board.json");
        TileDataManager.dataSave(tileManager2.getTiles(), "test2_board.json");

        try {
            TileManager tileLoad = new TileManager(TileDataManager.dataLoad("test_board.json"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String[] test= TileDataManager.dataGetFilenames();

        Scene scene = new Scene(root, 800, 600);
    }
}
