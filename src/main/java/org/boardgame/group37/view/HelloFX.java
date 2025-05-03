package org.boardgame.group37.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloFX extends Application {
    private Stage stage;
    private Pane root;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Snakes and Ladders");
        this.stage.setWidth(800);
        this.stage.setHeight(800);

        this.root = new Pane();
        StartPage.init(this.root);

        Scene scene = new Scene(this.root);
        this.stage.setScene(scene);
        this.stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
