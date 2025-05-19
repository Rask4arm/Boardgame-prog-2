package org.boardgame.group37.view.views;

import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import org.boardgame.group37.controller.MainController;
import org.boardgame.group37.view.ColorPalette;

public class VictoryPage {

    public static void init(Pane root, String winnerName, MainController mainController) {
        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        Label congratulations = new Label("Congratulations " + winnerName + ", you have won!");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(congratulations);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            mainController.initStartPage(root);
        });
        borderPane.setLeft(backButton);

        root.getChildren().addAll(borderPane);
    }
}
