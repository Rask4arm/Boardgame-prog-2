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

public class VictoryPage {

    public static void init(Pane root, String winnerName) {
        root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        Label congratulations = new Label("Congratulations " + winnerName + ", you have won!");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(congratulations);
        borderPane.prefWidthProperty().bind(root.widthProperty());
        borderPane.prefHeightProperty().bind(root.heightProperty());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            StartPage.init(root);
        });
        borderPane.setLeft(backButton);

        root.getChildren().addAll(borderPane);
    }
}
