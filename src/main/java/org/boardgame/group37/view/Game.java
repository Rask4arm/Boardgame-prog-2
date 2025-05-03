package org.boardgame.group37.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.boardgame.group37.model.game.GameManager;
import org.boardgame.group37.model.tile.Tile;
import org.boardgame.group37.model.tile.TileDataManager;
import org.boardgame.group37.model.tile.TileManager;

public class Game {
    public static void init(Pane root, BoardGraphic boardGraphic){
        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));


        PlayerToken[] playerToken = new PlayerToken[2];
        GameManager gameManager;
        playerToken[0] = new PlayerToken(Color.RED);
        playerToken[1] = new PlayerToken(Color.ORANGE);
        gameManager = new GameManager(boardGraphic.getTileManager());

        gameManager.getPlayerManager().playerAdd();
        gameManager.getPlayerManager().playerAdd();
        gameManager.getDieManager().dieAdd();
        gameManager.getDieManager().dieAdd();

        boardGraphic.updatePlayerPosition(playerToken[0], 1);
        boardGraphic.updatePlayerPosition(playerToken[1], 1);

        gameManager.printProperties();

        gameManager.gameStart();
        Button diceButton = new Button("Roll dice?");

        diceButton.setOnAction(e ->
                {gameManager.roundDie();
                    while (gameManager.getCurrentPlayerRolls() > 0) {
                        if (gameManager.getState().equals("end")) break;
                        try {
                            gameManager.roundMove();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        boardGraphic.updatePlayerPosition(playerToken[gameManager.getCurrentPlayerIndex()], gameManager.getCurrentPlayerPosition());
                    }
                    gameManager.roundEnd();
                }
        );

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            SnakesAndLaddersPage.init(root);
        });


        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(boardGraphic);
        borderPane.setRight(diceButton);
        borderPane.setLeft(quitButton);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());
        root.getChildren().addAll(borderPane);
    }
}
