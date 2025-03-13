package org.boardgame.group37.backend;

import org.boardgame.group37.backend.game.GameManager;

public class TestRun {
    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.getPlayerManager().playerAdd();
        gameManager.getPlayerManager().playerAdd();
        gameManager.getDieManager().dieAdd();
        gameManager.getDieManager().dieAdd();

        gameManager.printProperties();

        gameManager.gameStart();
        while (!gameManager.getState().equals("end")) {
            System.out.println("Current game state: " + gameManager.getState());
            System.out.println("\nDebug: New round, current player: " + gameManager.getCurrentPlayerIndex());
            gameManager.roundDie();
            while (gameManager.getCurrentPlayerRolls() > 0) {
                if (gameManager.getState().equals("end")) break;
                gameManager.roundMove();
            }   
            gameManager.roundEnd();
        }
    }
}

