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

import java.util.Objects;


public class CraftingBoardPage {
    public static void init(Pane root, PlayerManager playerManager) {
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
            int height = 10;
            int width = 10;

            if (!Objects.equals(textFieldHeight.getText(), "")) {
                System.out.println("Height: " + textFieldHeight.getText());
                if (Integer.parseInt(textFieldHeight.getText()) >= 1 ) {
                    height = Integer.parseInt(textFieldHeight.getText());
                }
            }
            if (!Objects.equals(textFieldWidth.getText(), "")) {
                System.out.println("Width: " + textFieldWidth.getText());
                if (Integer.parseInt(textFieldWidth.getText()) >= 1 ) {
                    width = Integer.parseInt(textFieldWidth.getText());
                }
            }

            TileManager tileManager = new TileManager(width, height * width, BOARDTYPES.SNAKE_AND_LADDERS);
            Game.init(root, new BoardGraphic(tileManager), playerManager);
        });

        Button saveBoardButton = new Button("Save Board");
        saveBoardButton.setOnAction(e -> {
            int height = 10;
            int width = 10;

            if (!Objects.equals(textFieldHeight.getText(), "")) {
                System.out.println("Height: " + textFieldHeight.getText());
                if (Integer.parseInt(textFieldHeight.getText()) >= 1 ) {
                    height = Integer.parseInt(textFieldHeight.getText());
                }
            }
            if (!Objects.equals(textFieldWidth.getText(), "")) {
                System.out.println("Width: " + textFieldWidth.getText());
                if (Integer.parseInt(textFieldWidth.getText()) >= 1 ) {
                    width = Integer.parseInt(textFieldWidth.getText());
                }
            }

            TileManager tileManager = new TileManager(width, height * width, BOARDTYPES.SNAKE_AND_LADDERS);

            // Save board data
            TileDataManager.dataSave(tileManager, textFieldName.getText() + ".json");
            System.out.println("Board saved as: " + textFieldName.getText() + ".json");
            Label successLabel = new Label("Board saved successfully!");
            vBox.getChildren().add(successLabel);
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
            SnakesAndLaddersPage.init(root);
        });
        borderPane.setLeft(backButton);

        root.getChildren().addAll(borderPane);
    }
}
