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
    public void start(Stage stage) throws Exception {
        boardGraphic = new BoardGraphic(5,10);
        playerToken[0] = new PlayerToken(Color.RED);
        playerToken[1] = new PlayerToken(Color.ORANGE);
        gameManager = new GameManager();

        gameManager.getPlayerManager().playerAdd();
        gameManager.getPlayerManager().playerAdd();
        gameManager.getDieManager().dieAdd();
        gameManager.getDieManager().dieAdd();

        gameManager.printProperties();

        gameManager.gameStart();
        Button diceButton = new Button("Roll dice?");

        diceButton.setOnAction(e ->
                {gameManager.roundDie();
                    while (gameManager.getCurrentPlayerRolls() > 0) {
                        if (gameManager.getState().equals("end")) break;
                        gameManager.roundMove();
                        boardGraphic.updatePlayerPosition(playerToken[gameManager.getCurrentPlayerIndex()], gameManager.getCurrentPlayerPosition());
                    }
                gameManager.roundEnd();
                }
        );

        boardGraphic.updatePlayerPosition(playerToken[0], 1);
        boardGraphic.updatePlayerPosition(playerToken[1], 1);

        VBox root = new VBox();
        root.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        root.getChildren().addAll(boardGraphic, diceButton);

        HBox root1 = new HBox();
        root1.setBackground(new Background(new BackgroundFill(ColorPalette.UI_BACKGROUND, CornerRadii.EMPTY, Insets.EMPTY)));

        Label l = new Label("Welcome to our snakes and ladders game");


        TileManager tileManager = new TileManager();
        tileManager.tileAdd(new Tile());
        tileManager.tileAdd(new Tile());

        TileManager tileManager2 = new TileManager();
        tileManager2.tileAdd(new Tile());
        tileManager2.tileAdd(new Tile());

        // Save and load board data
        TileDataManager.dataSave(tileManager.getTiles(), "test_board.json");
        TileDataManager.dataSave(tileManager2.getTiles(), "test2_board.json");

        TileManager tileLoad = new TileManager(TileDataManager.dataLoad("test_board.json"));
        String[] test= TileDataManager.dataGetFilenames();

        System.out.println(tileLoad);

        Label ltest = new Label(test[1]);
        root1.getChildren().addAll(l, ltest);
        Scene scene = new Scene(root1, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
