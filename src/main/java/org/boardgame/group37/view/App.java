package org.boardgame.group37.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.boardgame.group37.controller.MainController;
import org.boardgame.group37.view.views.StartPage;

public class App extends Application {
    private Stage stage;
    private Pane root;

    @Override
    public void start(Stage stage) {
        MainController mainController = new MainController();
        this.stage = stage;
        this.stage.setTitle("Snakes and Ladders and Monopoly");
        this.stage.setWidth(800);
        this.stage.setHeight(800);

        this.root = new Pane();
        StartPage.init(this.root, mainController);

        Scene scene = new Scene(this.root);
        this.stage.setScene(scene);
        this.stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
