package org.boardgame.group37.view;

import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        Label l = new Label("Welcome to our snakes and ladders game");
        Label v = new Label("Welcome to our snakes and ladders game");

        TileManager tileManager = new TileManager(10, 100, BOARDTYPES.SNAKE_AND_LADDERS);

        TileManager tileManager2 = new TileManager();
        tileManager2.tileAdd(new Tile());
        tileManager2.tileAdd(new Tile());

        // Save and load board data
        TileDataManager.dataSave(tileManager, "test_board.json");
        TileDataManager.dataSave(tileManager2, "test2_board.json");

        String[] filenames= TileDataManager.dataGetFilenames();

        root.getChildren().addAll(l);
        for (int i = 0; i < filenames.length; i++){
            Button filebutton = new Button(filenames[i]);

            filebutton.setOnAction(e ->
                    {
                        TileManager tileLoad = tileManager;
                        Game.init(root, new BoardGraphic(tileLoad));
                        try {
                            tileLoad = new TileManager(TileDataManager.dataLoad("test2_board.json").getTiles());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        org.boardgame.group37.view.BoardGraphic boardGraphic = new org.boardgame.group37.view.BoardGraphic(tileLoad);
                        Game.init(root, new BoardGraphic(tileLoad));
                        root.getChildren().add(boardGraphic);
                    }
            );

            root.getChildren().add(filebutton);
        }
    }
}

