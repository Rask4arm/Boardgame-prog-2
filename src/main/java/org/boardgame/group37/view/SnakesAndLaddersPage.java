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
        playerManager.playerAdd("Player 1", ColorPalette.PLAYER_RED);
        playerManager.playerAdd("Player 2", ColorPalette.PLAYER_BLUE);

        newBoardButton.setOnAction(e -> {

            Game.init(root, new BoardGraphic(tileManagerNew), playerManager);
        });
        borderPane.setBottom(newBoardButton);

        HBox hBoxPlayers = new HBox();

        TextField textField = new TextField();
        textField.setPromptText("Enter player name");
        hBoxPlayers.getChildren().add(textField);

        TextField textField2 = new TextField();
        textField2.setPromptText("Enter player name");
        hBoxPlayers.getChildren().add(textField2);

        Label l = new Label("how many players?");
        Label l1 = new Label("nothing selected");
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
                    textField.setPromptText("Enter player name");
                    hBoxPlayers.getChildren().add(textField);

                    Button submitButton = new Button("Submit");

                    int finalI = i;
                    submitButton.setOnAction(e -> {
                        String playerName = textField.getText();
                        if (!playerName.isEmpty()) {
                            Player player = new Player(playerName, ColorPalette.PLAYER_PURPLE);
                            playerManager.playerAdd(player);
                        }
                        else {
                            playerManager.playerAdd("Player " + (finalI + 1), ColorPalette.PLAYER_PURPLE);
                        }
                    });
                }
                for (int i = playerManager.getPlayers().size(); i > (new_value.intValue() + 2); i--) {
                    playerManager.playerRemove();
                    hBoxPlayers.getChildren().remove(hBoxPlayers.getChildren().size() - 1);
                }
            }
        });

        TilePane tilePane = new TilePane();
        tilePane.getChildren().addAll(hBox, l, choiceBox, l1, hBoxPlayers);
        borderPane.setCenter(tilePane);
        root.getChildren().addAll(borderPane);
    }
}
