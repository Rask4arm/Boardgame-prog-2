package org.boardgame.group37.view;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.boardgame.group37.model.game.GameManager;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.model.tile.Tile;
import org.boardgame.group37.model.tile.TileDataManager;
import org.boardgame.group37.model.tile.TileManager;



public class StartPage{
    public static void init(Pane root){
        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        Label welcome = new Label("Welcome to our snakes and ladders game");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(welcome);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());

        TileManager tileManager = new TileManager(10, 100, BOARDTYPES.SNAKE_AND_LADDERS);

        // Save and load board data
        TileDataManager.dataSave(tileManager, "test_board.json");

        String[] filenames= TileDataManager.dataGetFilenames();
        HBox hBox = new HBox();
        for (int i = 0; i < filenames.length; i++){
            Button filebutton = new Button(filenames[i]);

            filebutton.setOnAction(e ->
                    {
                        TileManager tileLoad = new TileManager(10, 100, BOARDTYPES.SNAKE_AND_LADDERS);
                        /**try {
                            tileLoad = new TileManager(TileDataManager.dataLoad("test_board.json").getWidth(),
                                    TileDataManager.dataLoad("test_board.json").getSize(), BOARDTYPES.SNAKE_AND_LADDERS);
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }*/
                        Game.init(root, new BoardGraphic(tileLoad));
                    }
            );
            hBox.getChildren().add(filebutton);
        }
        borderPane.setCenter(hBox);
        root.getChildren().addAll(borderPane);
    }
}

