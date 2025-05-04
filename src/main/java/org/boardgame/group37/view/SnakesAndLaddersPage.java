package org.boardgame.group37.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.model.tile.TileDataManager;
import org.boardgame.group37.model.tile.TileManager;

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
                        Game.init(root, new BoardGraphic(tileLoad));
                        System.out.println("test4");
                    }
            );
            hBox.getChildren().add(filebutton);
        }
        borderPane.setCenter(hBox);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            StartPage.init(root);
        });
        borderPane.setLeft(backButton);


        TileManager tileManagerNew = new TileManager(10, 20, BOARDTYPES.SNAKE_AND_LADDERS);
        Button newBoardButton = new Button("New Board");
        newBoardButton.setOnAction(e -> {

            Game.init(root, new BoardGraphic(tileManagerNew));
        });
        borderPane.setBottom(newBoardButton);


        root.getChildren().addAll(borderPane);
    }
}
