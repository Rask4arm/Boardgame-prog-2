package org.boardgame.group37.controller;
import org.boardgame.group37.model.game.GameManager;
import org.boardgame.group37.model.player.Player;

public class GameController {
    public GameManager gameManager;

    public GameController() {
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void gameStart() {
        gameManager.gameStart();
    }

    public void roundExecuteDie() {
        gameManager.roundDie();
    }

    public void roundExecuteMove() throws Exception {
        gameManager.roundMove();
    }

    public void roundExecuteEnd() {
        gameManager.roundEnd();
    }

    public void addDie() {
        gameManager.getDieManager().dieAdd();
    }

    public void removeDie() {
        gameManager.getDieManager().dieRemove();
    }

    public int getCurrentPlayerIndex() {
        return gameManager.getCurrentPlayerIndex();
    }

    public int getCurrentPlayerRolls() {
        return gameManager.getCurrentPlayerRolls();
    }

    public String getState() {
        return gameManager.getState();
    }

    public int getCurrentPlayerPosition() {
        return gameManager.getCurrentPlayerPosition();
    }

    public Player getPlayerWinner() {
        return gameManager.getPlayerWinner();
    }

}
