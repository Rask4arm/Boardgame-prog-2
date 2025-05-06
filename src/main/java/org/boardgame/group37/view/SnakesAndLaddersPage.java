package org.boardgame.group37.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerDataManager;
import org.boardgame.group37.model.player.PlayerManager;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.model.tile.TileDataManager;
import org.boardgame.group37.model.tile.TileManager;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;
import org.boardgame.group37.view.ColorPalette;

public class SnakesAndLaddersPage {
    public static void init(Pane root){
        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        Label welcome = new Label("Welcome to our snakes and ladders game");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(welcome);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());

        TileManager tileManager = new TileManager(10, 100, BOARDTYPES.SNAKE_AND_LADDERS);
        TileManager tileManager2 = new TileManager(10, 50, BOARDTYPES.SNAKE_AND_LADDERS);

        // Save and load board data
        TileDataManager.dataSave(tileManager, "test_board.json");
        TileDataManager.dataSave(tileManager2, "test_board2.json");

        String[] filenames= TileDataManager.dataGetFilenames();
        HBox hBox = new HBox();
        for (int i = 0; i < filenames.length; i++){
            String filename = filenames[i];
            Button filebutton = new Button(filename);

            filebutton.setOnAction(e ->
                    {
                        System.out.println("test1");
                        TileManager tileLoad;
                        System.out.println("test2");
                        try {
                            tileLoad = new TileManager(TileDataManager.dataLoad(filename));
                            System.out.println("test3");
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        Game.init(root, new BoardGraphic(tileLoad), new PlayerManager());
                        System.out.println("test4");
                    }
            );
            hBox.getChildren().add(filebutton);
        }

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            StartPage.init(root);
        });
        borderPane.setLeft(backButton);


        TileManager tileManagerNew = new TileManager(10, 70, BOARDTYPES.SNAKE_AND_LADDERS);
        Button newBoardButton = new Button("New Board");

        PlayerManager playerManager = new PlayerManager();
        Color colorP1 = ColorPalette.PLAYER_RED;
        Color colorP2 = ColorPalette.PLAYER_BLUE;
        playerManager.playerAdd("Player 1", colorP1);
        playerManager.playerAdd("Player 2", colorP2);

        HBox hBoxPlayers = new HBox();

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
                playerManager.changePlayerColor(0, color);
                colorChoiceBoxP1.setStyle("-fx-background-color: #" + colors[new_value.intValue()].toString().substring(2) + ";");
            }
        });

        Button savePlayer1Button = new Button("Save Player");
        savePlayer1Button.setOnAction(e -> {
            PlayerDataManager.dataSave(playerManager.getPlayer(0));
        });

        VBox vBoxPlayer1 = new VBox();
        vBoxPlayer1.getChildren().addAll(textFieldPlayer1, colorChoiceBoxP1, savePlayer1Button);
        hBoxPlayers.getChildren().add(vBoxPlayer1);

        textFieldPlayer1.setOnKeyTyped(e -> {
            String playerName = textFieldPlayer1.getText();
            if (!playerName.isEmpty()) {
                playerManager.changePlayerName(0, playerName);
            }
            else {
                playerManager.changePlayerName(1, "Player 1");
            }
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
                playerManager.changePlayerColor(1, color);
                colorChoiceBoxP2.setStyle("-fx-background-color: #" + colors[new_value.intValue()].toString().substring(2) + ";");
            }
        });

        Button savePlayer2Button = new Button("Save Player");
        savePlayer2Button.setOnAction(e -> {
            PlayerDataManager.dataSave(playerManager.getPlayer(1));
        });

        VBox vBoxPlayer2 = new VBox();
        vBoxPlayer2.getChildren().addAll(textFieldPlayer2, colorChoiceBoxP2, savePlayer2Button);
        hBoxPlayers.getChildren().add(vBoxPlayer2);

        textFieldPlayer2.setOnKeyTyped(e -> {
            String playerName = textFieldPlayer2.getText();
            if (!playerName.isEmpty()) {
                playerManager.changePlayerName(1, playerName);
            }
            else {
                playerManager.changePlayerName(1, "Player 2");
            }

        });

        newBoardButton.setOnAction(e -> {

            Game.init(root, new BoardGraphic(tileManagerNew), playerManager);
        });
        borderPane.setBottom(newBoardButton);



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

                for (int i = playerManager.getPlayers().size(); i < (new_value.intValue() + 2); i++) {
                    TextField textField = new TextField();
                    textField.setPromptText("Player " + (i + 1));

                    int finalI = i;
                    textField.setOnKeyTyped(e -> {
                        String playerName = textField.getText();
                        if (!playerName.isEmpty()) {
                            playerManager.changePlayerName(finalI, playerName);
                        }
                        else {
                            playerManager.changePlayerName(finalI, "Player " + (finalI + 1));
                        }
                    });

                    ChoiceBox colorChoiceBox = new ChoiceBox(FXCollections.observableArrayList(colors));
                    colorChoiceBox.setValue(colors[finalI]);

                    colorChoiceBox.setStyle("-fx-background-color: #" + colors[finalI].toString().substring(2) + ";");

                    colorChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

                        // if the item of the list is changed
                        public void changed(ObservableValue ov, Number value, Number new_value)
                        {
                            // set the text for the label to the selected color
                            Color color = colors[new_value.intValue()];
                            playerManager.changePlayerColor(finalI, color);
                            colorChoiceBox.setStyle("-fx-background-color: #" + colors[new_value.intValue()].toString().substring(2) + ";");
                        }
                    });

                    Button savePlayerButton = new Button("Save Player");
                    savePlayerButton.setOnAction(e -> {
                        PlayerDataManager.dataSave(playerManager.getPlayer(finalI));
                    });

                    VBox vBoxPlayers = new VBox();
                    vBoxPlayers.getChildren().addAll(textField, colorChoiceBox, savePlayerButton);
                    hBoxPlayers.getChildren().add(vBoxPlayers);

                    playerManager.playerAdd("Player " + (i + 1), ColorPalette.getPlayerColors()[i]);
                }
                for (int i = playerManager.getPlayers().size(); i > (new_value.intValue() + 2); i--) {
                    playerManager.playerRemove((i-1));
                    hBoxPlayers.getChildren().removeLast();
                }
            }
        });


        TilePane tilePane = new TilePane();
        tilePane.getChildren().addAll(hBox, l, choiceBox, l1, hBoxPlayers);
        borderPane.setCenter(tilePane);
        root.getChildren().addAll(borderPane);
    }
}
