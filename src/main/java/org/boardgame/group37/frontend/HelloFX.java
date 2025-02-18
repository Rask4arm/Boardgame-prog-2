package org.boardgame.group37.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloFX extends Application {
    BoardGraphic boardGraphic;

    @Override
    public void start(Stage stage) {
        boardGraphic = new BoardGraphic(10,10);
        Label l = new Label("Welcome to our snakes and ladders game");
        Scene scene = new Scene(new StackPane(boardGraphic), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
