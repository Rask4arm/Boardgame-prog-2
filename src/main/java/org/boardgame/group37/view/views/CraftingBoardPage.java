package org.boardgame.group37.view.views;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.boardgame.group37.controller.MainController;
import org.boardgame.group37.model.player.PlayerManager;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.model.tile.TileDataManager;
import org.boardgame.group37.model.tile.TileManager;
import org.boardgame.group37.view.BoardGraphic;
import org.boardgame.group37.view.ColorPalette;

import java.util.Objects;


public class CraftingBoardPage {
    public static void init(Pane root, MainController mainController) {
        // Initialize the crafting board page
        System.out.println("Crafting board page initialized.");

        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox vBox = new VBox();

        TextField textFieldName = new TextField();
        textFieldName.setPromptText("New Board");
        textFieldName.setText("New Board");

        TextField textFieldHeight = new TextField();
        textFieldHeight.setPromptText("10");

        TextField textFieldWidth = new TextField();
        textFieldWidth.setPromptText("10");

        // force the field to be numeric only
        textFieldHeight.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldHeight.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // force the field to be numeric only
        textFieldWidth.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textFieldWidth.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Button createBoardButton = new Button("Create Board");
        createBoardButton.setOnAction(e -> {
            mainController.createBoardButton(textFieldHeight.getText(), textFieldWidth.getText(), root);
        });

        Button saveBoardButton = new Button("Save Board");
        saveBoardButton.setOnAction(e -> {
            mainController.saveBoardButton(textFieldName.getText(), textFieldHeight.getText(), textFieldWidth.getText(), vBox);
        });

        Label label = new Label("Create your own board");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(label);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());


        vBox.getChildren().addAll(
                new Label("Name your board:"),
                textFieldName,
                new Label("Height:"),
                textFieldHeight,
                new Label("Width:"),
                textFieldWidth,
                createBoardButton,
                saveBoardButton
        );

        borderPane.setCenter(vBox);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            mainController.initSnakesAndLadders(root, mainController);
        });
        borderPane.setLeft(backButton);

        root.getChildren().addAll(borderPane);
    }
}
