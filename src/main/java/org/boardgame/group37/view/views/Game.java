package org.boardgame.group37.view.views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import org.boardgame.group37.controller.MainController;
import org.boardgame.group37.model.game.GameManager;
import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerManager;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.view.BoardGraphic;
import org.boardgame.group37.view.ColorPalette;
import org.boardgame.group37.view.PlayerToken;

import java.util.ArrayList;

public class Game {
    public static void init(Pane root, BoardGraphic boardGraphic, MainController mainController) {
        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        ArrayList<Player> players = mainController.getPlayerController().getPlayers();
        int numberOfPlayers = players.size();
        PlayerToken[] playerToken = new PlayerToken[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            playerToken[i] = new PlayerToken(players.get(i).getColor());
            boardGraphic.updatePlayerPosition(playerToken[i], 1);
        }

        mainController.getGameController().setGameManager(new GameManager(boardGraphic.getTileManager(), mainController.getPlayerController().getPlayerManager(), boardGraphic.getBoardType()));

        mainController.getGameController().addDie();
        mainController.getGameController().addDie();

        VBox vbox = new VBox();
        HBox hbox = new HBox();

        Label currentPlayerLabel = new Label("Current Player: " + players.get(mainController.getGameController().getCurrentPlayerIndex()).getName());

        vbox.getChildren().addAll(boardGraphic, currentPlayerLabel);

        if (boardGraphic.getBoardType() == BOARDTYPES.MONOPOLY) {
            for (int i = 0; i < numberOfPlayers; i++) {
                Label playerLabel = new Label(players.get(i).getName() + " has " + players.get(i).getMoney() + "Monopoly Dollars");
                playerLabel.setTextFill(players.get(i).getColor());
                hbox.getChildren().add(playerLabel);
            }
            vbox.getChildren().add(hbox);
        }

        mainController.getGameController().gameStart();
        Button diceButton = new Button("Roll dice?");

        Label diceRollLabel = new Label("");
        VBox diceRollBox = new VBox();
        diceRollBox.getChildren().addAll(diceButton, diceRollLabel);

        diceButton.setOnAction(e -> {
            mainController.diceButton(boardGraphic, mainController, players, root, currentPlayerLabel, playerToken, hbox, numberOfPlayers, diceRollBox);
        });

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            mainController.initStartPage(root);
        });

        HBox unmovable = new HBox();
        unmovable.getChildren().add(vbox);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(unmovable);
        borderPane.setRight(diceRollBox);
        borderPane.setLeft(quitButton);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());
        root.getChildren().addAll(borderPane);
    }
}
