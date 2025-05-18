package org.boardgame.group37.controller;
import org.boardgame.group37.model.game.GameManager;

public class GameController {
    public GameManager gameManager;

    public GameController() {
        // Constructor
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void gameStart(GameManager gameManager) {
        gameManager.gameStart();
    }

    public void roundExecuteDie(GameManager gameManager) {
        gameManager.roundDie();
    }

    public void roundExecuteMove(GameManager gameManager) throws Exception {
        gameManager.roundMove();
    }

    public void roundExecuteEnd(GameManager gameManager) {
        gameManager.roundEnd();
    }

}
