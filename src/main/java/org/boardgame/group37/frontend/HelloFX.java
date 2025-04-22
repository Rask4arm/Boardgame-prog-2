package org.boardgame.group37.frontend;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.boardgame.group37.backend.game.GameManager;
import org.boardgame.group37.backend.tile.Tile;
import org.boardgame.group37.backend.tile.TileDataManager;
import org.boardgame.group37.backend.tile.TileManager;

import java.util.concurrent.TimeUnit;

public class HelloFX extends Application {
    BoardGraphic boardGraphic;
    PlayerToken[] playerToken = new PlayerToken[2];
    GameManager gameManager;

    @Override
    public void start(Stage stage) {
        StartPage.init(stage);
    }

    public static void main(String[] args) {
        launch();
    }

}
