package org.boardgame.group37.model;

import org.boardgame.group37.model.game.GameManager;
import org.boardgame.group37.model.tile.BOARDTYPES;

/**
 * TestRun class
 * This class tests a full backend run of the game.
 * It includes debugging information and prints the game as it progresses.
 * It is not intended for production use.
 */
public class TestRun {
    public static void main(String[] args) throws Exception {
        GameManager gameManager = new GameManager(10, 30, BOARDTYPES.MONOPOLY);

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

