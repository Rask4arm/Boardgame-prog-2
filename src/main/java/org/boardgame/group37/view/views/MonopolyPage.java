package org.boardgame.group37.view.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.boardgame.group37.controller.MainController;
import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerDataManager;
import org.boardgame.group37.model.player.PlayerManager;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.model.tile.TileManager;
import org.boardgame.group37.view.BoardGraphic;
import org.boardgame.group37.view.ColorPalette;
import org.boardgame.group37.view.LoadedPlayersChoiceBox;

import java.util.ArrayList;

public class MonopolyPage {
    public static void init(Pane root, MainController mainController){
        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        Label welcome = new Label("Welcome to our monopoly game");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(welcome);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());

        HBox hBoxFiles = new HBox();



        mainController.getPlayerController().setPlayerManager(new PlayerManager());

        mainController.getPlayerController().setStartPlayers();
        Color colorP1 = ColorPalette.PLAYER_RED;
        Color colorP2 = ColorPalette.PLAYER_BLUE;


        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            mainController.initStartPage(root);
        });
        borderPane.setLeft(backButton);


        Button startGameButton = new Button("Start Game");


        HBox hBoxPlayers = new HBox();
        VBox vBoxPlayer1 = new VBox();
        VBox vBoxPlayer2 = new VBox();

        TextField textFieldPlayer1 = new TextField();
        textFieldPlayer1.setPromptText("Player 1");

        Color[] colors = ColorPalette.getPlayerColors();
        ChoiceBox colorChoiceBoxP1 = new ChoiceBox(FXCollections.observableArrayList(colors));
        colorChoiceBoxP1.setValue(colorP1);
        colorChoiceBoxP1.setStyle("-fx-background-color: #" + colorP1.toString().substring(2) + ";");

        colorChoiceBoxP1.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                // set the text for the label to the selected color
                Color color = colors[new_value.intValue()];
                mainController.getPlayerController().changePlayerColor(0, color);
                colorChoiceBoxP1.setStyle("-fx-background-color: #" + colors[new_value.intValue()].toString().substring(2) + ";");
            }
        });

        ArrayList<Player> loadedPlayers = PlayerDataManager.dataLoad();

        Button savePlayer1Button = new Button("Save Player");

        savePlayer1Button.setOnAction(e -> {
            mainController.savePlayerButton(vBoxPlayer1, colorChoiceBoxP1, textFieldPlayer1, loadedPlayers, 0);
        });

        ChoiceBox player1ChoiceBox = LoadedPlayersChoiceBox.LoadedPlayersChoiceBox(loadedPlayers, colorChoiceBoxP1, textFieldPlayer1, mainController);

        vBoxPlayer1.getChildren().addAll(textFieldPlayer1, colorChoiceBoxP1, savePlayer1Button, player1ChoiceBox);
        hBoxPlayers.getChildren().add(vBoxPlayer1);

        textFieldPlayer1.setOnKeyTyped(e -> {
            mainController.playerTextfield(textFieldPlayer1, player1ChoiceBox, 0);
        });


        TextField textFieldPlayer2 = new TextField();
        textFieldPlayer2.setPromptText("Player 2");
        ChoiceBox colorChoiceBoxP2 = new ChoiceBox(FXCollections.observableArrayList(colors));
        colorChoiceBoxP2.setValue(colorP2);
        colorChoiceBoxP2.setStyle("-fx-background-color: #" + colorP2.toString().substring(2) + ";");

        colorChoiceBoxP2.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                // set the text for the label to the selected color
                Color color = colors[new_value.intValue()];
                mainController.getPlayerController().changePlayerColor(1, color);
                colorChoiceBoxP2.setStyle("-fx-background-color: #" + colors[new_value.intValue()].toString().substring(2) + ";");
            }
        });

        Button savePlayer2Button = new Button("Save Player");
        savePlayer2Button.setOnAction(e -> {
            mainController.savePlayerButton(vBoxPlayer2, colorChoiceBoxP2, textFieldPlayer2, loadedPlayers, 1);
        });

        ChoiceBox player2ChoiceBox = LoadedPlayersChoiceBox.LoadedPlayersChoiceBox(loadedPlayers, colorChoiceBoxP2, textFieldPlayer2, mainController);

        vBoxPlayer2.getChildren().addAll(textFieldPlayer2, colorChoiceBoxP2, savePlayer2Button, player2ChoiceBox);
        hBoxPlayers.getChildren().add(vBoxPlayer2);

        textFieldPlayer2.setOnKeyTyped(e -> {
            mainController.playerTextfield(textFieldPlayer2, player2ChoiceBox, 1);
        });

        startGameButton.setOnAction(e -> {
            try {
                mainController.initGamePage(root, new BoardGraphic(new TileManager(10, 40, BOARDTYPES.MONOPOLY), BOARDTYPES.MONOPOLY));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        borderPane.setBottom(startGameButton);



        Label l = new Label("how many players?");
        Label l1 = new Label("2 selected");
        ChoiceBox choiceBox = new ChoiceBox(FXCollections.observableArrayList(2,3,4,5));
        choiceBox.setValue(2);

        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            // if the item of the list is changed
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                // set the text for the label to the selected item
                l1.setText((new_value.intValue() + 2) + " selected");

                for (int i = mainController.getPlayerController().getNumberOfPlayers(); i < (new_value.intValue() + 2); i++) {
                    TextField textField = new TextField();
                    textField.setPromptText("Player " + (i + 1));

                    int finalI = i;
                    VBox vBoxPlayers = new VBox();

                    ChoiceBox colorChoiceBox = new ChoiceBox(FXCollections.observableArrayList(colors));
                    colorChoiceBox.setValue(colors[finalI]);

                    colorChoiceBox.setStyle("-fx-background-color: #" + colors[finalI].toString().substring(2) + ";");

                    colorChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

                        // if the item of the list is changed
                        public void changed(ObservableValue ov, Number value, Number new_value)
                        {
                            // set the text for the label to the selected color
                            Color color = colors[new_value.intValue()];
                            mainController.getPlayerController().changePlayerColor(finalI, color);
                            colorChoiceBox.setStyle("-fx-background-color: #" + colors[new_value.intValue()].toString().substring(2) + ";");
                        }
                    });

                    Button savePlayerButton = new Button("Save Player");
                    savePlayerButton.setOnAction(e -> {
                        mainController.savePlayerButton(vBoxPlayers, colorChoiceBox, textField, loadedPlayers, finalI);
                    });

                    ChoiceBox playerChoiceBox = LoadedPlayersChoiceBox.LoadedPlayersChoiceBox(loadedPlayers, colorChoiceBox, textField, mainController);

                    textField.setOnKeyTyped(e -> {
                        mainController.playerTextfield(textField, playerChoiceBox, finalI);
                    });

                    vBoxPlayers.getChildren().addAll(textField, colorChoiceBox, savePlayerButton, playerChoiceBox);
                    hBoxPlayers.getChildren().add(vBoxPlayers);

                    mainController.getPlayerController().addPlayer("Player " + (i + 1), colors[i]);
                }
                for (int i = mainController.getPlayerController().getNumberOfPlayers(); i > (new_value.intValue() + 2); i--) {
                    mainController.getPlayerController().removePlayer(i-1);
                    hBoxPlayers.getChildren().removeLast();
                }
            }
        });


        TilePane tilePane = new TilePane();
        tilePane.getChildren().addAll(hBoxFiles, l, choiceBox, l1, hBoxPlayers);
        borderPane.setCenter(tilePane);
        root.getChildren().addAll(borderPane);
    }
}
