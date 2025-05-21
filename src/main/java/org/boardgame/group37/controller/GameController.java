package org.boardgame.group37.controller;
import org.boardgame.group37.model.game.GameManager;
import org.boardgame.group37.model.player.Player;

/**
 * GameController class
 */
public class GameController {
    public GameManager gameManager;

    /**
     * Constructor for GameController
     */
    public GameController() {
    }

    /**
     * Constructor for GameController with GameManager
     * @param gameManager the GameManager to be set
     */
    public GameManager getGameManager() {
        return gameManager;
    }

    /**
     * Sets the GameManager
     * @param gameManager the GameManager to be set
     */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * Starts the game
     */
    public void gameStart() {
        gameManager.gameStart();
    }

    /**
     * Executes round die throw
     */
    public void roundExecuteDie() {
        gameManager.roundDie();
    }

    /**
     * Executes round move
     */
    public void roundExecuteMove() throws Exception {
        gameManager.roundMove();
    }

    /**
     * Executes round end
     */
    public void roundExecuteEnd() {
        gameManager.roundEnd();
    }

    /**
     * Adds a die to the game
     */
    public void addDie() {
        gameManager.getDieManager().dieAdd();
    }

    /**
     * Removes a die from the game
     */
    public void removeDie() {
        gameManager.getDieManager().dieRemove();
    }

    /**
     * Gets the current player turn index
     * @return the current player index
     */
    public int getCurrentPlayerIndex() {
        return gameManager.getCurrentPlayerIndex();
    }

    /**
     * Gets the rolls from the dice thrown left
     * @return the number of rolls left
     */
    public int getCurrentPlayerRolls() {
        return gameManager.getCurrentPlayerRolls();
    }

    /**
     * Gets the current state of the game
     * @return the current state of the game
     */
    public String getState() {
        return gameManager.getState();
    }

    /**
     * Gets the current player position
     * @return the current player position
     */
    public int getCurrentPlayerPosition() {
        return gameManager.getCurrentPlayerPosition();
    }

    /**
     * Gets the winner of the game
     * @return the current player OR null if not found
     */
    public Player getPlayerWinner() {
        return gameManager.getPlayerWinner();
    }

}
