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
import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerManager;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.model.tile.Tile;
import org.boardgame.group37.model.tile.TileDataManager;
import org.boardgame.group37.model.tile.TileManager;

import java.util.ArrayList;

public class Game {
    public static void init(Pane root, BoardGraphic boardGraphic, PlayerManager playerManager) {
        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        ArrayList<Player> players = playerManager.getPlayers();
        int numberOfPlayers = players.size();
        PlayerToken[] playerToken = new PlayerToken[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            playerToken[i] = new PlayerToken(players.get(i).getColor());
            boardGraphic.updatePlayerPosition(playerToken[i], 1);
        }

        GameManager gameManager;
        gameManager = new GameManager(boardGraphic.getTileManager(), playerManager, BOARDTYPES.SNAKE_AND_LADDERS);

        gameManager.getDieManager().dieAdd();
        gameManager.getDieManager().dieAdd();

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
                    if (gameManager.getState().equals("end")) {
                        String winnerName = gameManager.getPlayerWinner().getName();
                        VictoryPage.init(root, winnerName);
                    }
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
