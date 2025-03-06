/*package org.boardgame.group37.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.boardgame.group37.backend.managers.GameManager;

public class HelloFX extends Application {
    BoardGraphic boardGraphic;
    PlayerToken playerToken;
    GameManager gameManager;


    @Override
    public void start(Stage stage) {
        boardGraphic = new BoardGraphic(10,10);
        playerToken = new PlayerToken(Color.RED);
        gameManager = new GameManager();
        gameManager.gameStart();

        Button diceButton = new Button("Roll dice?");

        diceButton.setOnAction(e ->
                {gameManager.roundStart();
                gameManager.roundRoll();
                gameManager.roundMove();
                gameManager.roundEnd();
                System.out.println("find this" + gameManager.getPlayerCurrentIndex());
                boardGraphic.updatePlayerPosition(playerToken, gameManager.getPlayerCurrentIndex());
                }
        );

        boardGraphic.updatePlayerPosition(playerToken, 1);

        VBox root = new VBox();

        root.getChildren().addAll(boardGraphic, diceButton);


        Label l = new Label("Welcome to our snakes and ladders game");
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
*/