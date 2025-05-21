package org.boardgame.group37.view.views;

import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.boardgame.group37.controller.MainController;
import org.boardgame.group37.view.ColorPalette;

/**
 * StartPage class
 * This class is responsible for creating the start page.
 */
public class StartPage{
    public static void init(Pane root, MainController mainController){
        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        Label welcome = new Label("Welcome to our game");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(welcome);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());

        Button SnakesAndLaddersButton = new Button("Snakes and Ladders");
        SnakesAndLaddersButton.setOnAction(e -> {
            mainController.initSnakesAndLadders(root, mainController);
        });

        Button MonopolyButton = new Button("Monopoly");
        MonopolyButton.setOnAction(e -> {
            mainController.initMonopoly(root, mainController);
        });

        borderPane.setLeft(SnakesAndLaddersButton);
        borderPane.setRight(MonopolyButton);
        root.getChildren().addAll(borderPane);
    }
}

