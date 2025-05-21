package org.boardgame.group37.controller;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerDataManager;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.model.tile.TileDataManager;
import org.boardgame.group37.model.tile.TileManager;
import org.boardgame.group37.view.BoardGraphic;
import org.boardgame.group37.view.LoadedPlayersChoiceBox;
import org.boardgame.group37.view.PlayerToken;
import org.boardgame.group37.view.views.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 * MainController class
 * This class is responsible for managing the game controllers and initializing different game pages.
 */
public class MainController {
    private final GameController gameController;
    private final PlayerController playerController;
    private final BoardController boardController;

    /**
     * Gets the GameController
     * @return GameController
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Gets the PlayerController
     * @return PlayerController
     */
    public PlayerController getPlayerController() {
        return playerController;
    }

    /**
     * Gets the BoardController
     * @return BoardController
     */
    public BoardController getBoardController() {
        return boardController;
    }

    /**
     * Constructor for MainController
     * Initializes the GameController, PlayerController, and BoardController.
     */
    public MainController() {
        this.gameController = new GameController();
        this.playerController = new PlayerController();
        this.boardController = new BoardController();
    }

    /**
     * Initializes the Monopoly game page
     * @param root the root pane
     * @param mainController the main controller
     */
    public void initMonopoly(Pane root, MainController mainController) {
        MonopolyPage.init(root, mainController);
    }

    /**
     * Initializes the Snakes and Ladders game page
     * @param root the root pane
     * @param mainController the main controller
     */
    public void initSnakesAndLadders(Pane root, MainController mainController) {
        SnakesAndLaddersPage.init(root, mainController);
    }

    /**
     * Initializes the Crafting Board page
     * @param root the root pane
     * @param mainController the main controller
     */
    public void initCraftingBoard(Pane root, MainController mainController) {
        CraftingBoardPage.init(root, mainController);

    }

    /**
     * Initializes the Start page
     * @param root the root pane
     */
    public void initStartPage(Pane root) {
        StartPage.init(root, this);
    }

    /**
     * Initializes the Game page
     * @param root the root pane
     * @param mainController the main controller
     */
    public void initGamePage(Pane root, BoardGraphic boardGraphic) {
        Game.init(root, boardGraphic, this);
    }

    /**
     * Creates a board button
     * @param textFieldHeight the height of the board
     * @param textFieldWidth the width of the board
     * @param root the root pane
     */
    public void createBoardButton(String textFieldHeight, String textFieldWidth, Pane root) {
        int height = 10;
        int width = 10;

        if (!Objects.equals(textFieldHeight, "")) {
            System.out.println("Height: " + textFieldHeight);
            if (Integer.parseInt(textFieldHeight) >= 1) {
                height = Integer.parseInt(textFieldHeight);
            }
        }
        if (!Objects.equals(textFieldWidth, "")) {
            System.out.println("Width: " + textFieldWidth);
            if (Integer.parseInt(textFieldWidth) >= 1) {
                width = Integer.parseInt(textFieldWidth);
            }
        }

        try {
            TileManager tileManager = new TileManager(width, height * width, BOARDTYPES.SNAKE_AND_LADDERS);
            initGamePage(root, new BoardGraphic(tileManager, BOARDTYPES.SNAKE_AND_LADDERS));
        } catch (Exception ex) {
            System.out.println("Error creating board: " + ex.getMessage());
        }
    }

    /**
     * Saves the board button
     * @param textFieldName Name of the board text
     * @param textFieldHeight Height of the board text
     * @param textFieldWidth Width of the board text
     * @param vBox the VBox to add the success label
     */
    public void saveBoardButton(String textFieldName, String textFieldHeight, String textFieldWidth, VBox vBox) {
        int height = 10;
        int width = 10;

        if (!Objects.equals(textFieldHeight, "")) {
            System.out.println("Height: " + textFieldHeight);
            if (Integer.parseInt(textFieldHeight) >= 1 ) {
                height = Integer.parseInt(textFieldHeight);
            }
        }
        if (!Objects.equals(textFieldWidth, "")) {
            System.out.println("Width: " + textFieldWidth);
            if (Integer.parseInt(textFieldWidth) >= 1 ) {
                width = Integer.parseInt(textFieldWidth);
            }
        }

        try {
            TileManager tileManager = new TileManager(width, height * width, BOARDTYPES.SNAKE_AND_LADDERS);

            // Save board data
            TileDataManager.dataSave(tileManager, textFieldName + ".json");
            System.out.println("Board saved as: " + textFieldName + ".json");
            Label successLabel = new Label("Board saved successfully!");
            vBox.getChildren().add(successLabel);
        } catch (Exception ex) {
            System.out.println("Error saving board: " + ex.getMessage());
        }
    }

    /**
     * Executes the dice button action
     * @param boardGraphic the board graphic
     * @param mainController the main controller
     * @param players the list of players
     * @param root the root pane
     * @param currentPlayerLabel the label for the current player
     * @param playerToken the player tokens
     * @param hbox the HBox to display player information
     * @param numberOfPlayers the number of players
     * @param diceRollBox the VBox to display the dice roll
     */
    public void diceButton(BoardGraphic boardGraphic, MainController mainController, ArrayList<Player> players , Pane root, Label currentPlayerLabel, PlayerToken[] playerToken, HBox hbox, int numberOfPlayers, VBox diceRollBox) {
        gameController.roundExecuteDie();
        Label diceRollLabel = new Label("" + gameController.getCurrentPlayerRolls());

        diceRollBox.getChildren().remove(1);
        diceRollBox.getChildren().add(diceRollLabel);

        while (gameController.getCurrentPlayerRolls() > 0) {
            if (gameController.getState().equals("end")) break;
            try {
                gameController.roundExecuteMove();
                boardGraphic.updatePlayerPosition(playerToken[gameController.getCurrentPlayerIndex()], gameController.getCurrentPlayerPosition());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }


        }
        gameController.roundExecuteEnd();
        if (gameController.getState().equals("end")) {
            String winnerName = gameController.getPlayerWinner().getName();
            VictoryPage.init(root, winnerName, mainController);
        }
        currentPlayerLabel.setText("Current Player: " + players.get(gameController.getCurrentPlayerIndex()).getName());

        if (boardGraphic.getBoardType() == BOARDTYPES.MONOPOLY) {
            hbox.getChildren().clear();
            for (int i = 0; i < numberOfPlayers; i++) {
                Label playerLabel = new Label(players.get(i).getName() + " has " + players.get(i).getMoney() + "Monopoly Dollars");
                playerLabel.setTextFill(players.get(i).getColor());
                hbox.getChildren().add(playerLabel);
            }
        }
    }

    /**
     * Saves the player button
     * @param vBoxPlayer the VBox to add the player choice box
     * @param colorChoiceBox the choice box for player color
     * @param textFieldPlayer the text field for player name
     * @param loadedPlayers the list of loaded players
     * @param index the index of the player
     */
    public void savePlayerButton(VBox vBoxPlayer, ChoiceBox colorChoiceBox, TextField textFieldPlayer, ArrayList<Player> loadedPlayers, int index) {
        PlayerDataManager.dataSave(getPlayerController().getPlayer(index));
        loadedPlayers.clear();
        loadedPlayers.addAll(PlayerDataManager.dataLoad());
        ChoiceBox playerChoiceBox = LoadedPlayersChoiceBox.LoadedPlayersChoiceBox(loadedPlayers, colorChoiceBox, textFieldPlayer, this);

        vBoxPlayer.getChildren().remove(3);
        vBoxPlayer.getChildren().add(playerChoiceBox);
    }

    /**
     * Handles the player text field input
     * @param textFieldPlayer1 the text field for player name
     * @param player1ChoiceBox the choice box for player color
     * @param index the index of the player
     */
    public void playerTextfield(TextField textFieldPlayer1, ChoiceBox player1ChoiceBox, int index) {
        String playerName = textFieldPlayer1.getText();
        player1ChoiceBox.setValue(null);
        if (!playerName.isEmpty()) {
            getPlayerController().getPlayerManager().changePlayerName(index, playerName);
        }
        else {
            getPlayerController().getPlayerManager().changePlayerName(index, "Player 1");
        }
    }

    /**
     * Handles the file button action
     * @param filename the name of the file to load
     * @param root the root pane
     */
    public void fileButton(String filename, Pane root) {
        TileManager tileLoad;
        try {
            tileLoad = TileDataManager.dataLoad(filename);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        initGamePage(root, new BoardGraphic(tileLoad, BOARDTYPES.SNAKE_AND_LADDERS));
    }

}
