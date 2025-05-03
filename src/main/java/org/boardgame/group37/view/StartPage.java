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

        Label welcome = new Label("Welcome to our game");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(welcome);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());

        Button SnakesAndLaddersButton = new Button("Snakes and Ladders");
        SnakesAndLaddersButton.setOnAction(e -> {
            SnakesAndLaddersPage.init(root);
        });

        Button MonopolyButton = new Button("Monopoly");
        MonopolyButton.setOnAction(e -> {
            MonopolyPage.init(root);
        });

        borderPane.setLeft(SnakesAndLaddersButton);
        borderPane.setRight(MonopolyButton);
        root.getChildren().addAll(borderPane);
    }
}

