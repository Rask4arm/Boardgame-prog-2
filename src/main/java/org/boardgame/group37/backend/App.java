package org.boardgame.group37.backend;

public class App {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.getPlayerManager().playerAdd();
        gameManager.getPlayerManager().playerAdd();
        gameManager.getDieManager().dieAdd();
        gameManager.getDieManager().dieAdd();
        gameManager.gameStart();
        gameManager.roundDie();
        while (gameManager.getCurrentPlayerRolls() > 0) {
            gameManager.roundMove();
        }   gameManager.roundEnd();
    }
}

