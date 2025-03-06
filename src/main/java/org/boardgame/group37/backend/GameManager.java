package org.boardgame.group37.backend;

import org.boardgame.group37.backend.*;
import java.util.ArrayList;

public class GameManager {
    private TileManager tileManager;
    private PlayerManager playerManager;
    private DieManager dieManager;
    private int currentPlayerIndex;
    private int currentPlayerRolls;
    private String playerWinner;
    private String state;

    /*
    *   STATE = "menu" --> Menu screen
    *   STATE = "game" --> Game is running
    *   STATE = "end" --> Game has ended
    */

    public GameManager() {
        gameReset(true, true, true);
    }

    public void gameReset(boolean resetPlayers, boolean resetTiles, boolean resetDie) {
        if (resetTiles) tileManager = new TileManager(10, 10);
        if (resetPlayers) playerManager = new PlayerManager();
        if (resetDie) dieManager = new DieManager();
        currentPlayerIndex = 0;
        currentPlayerRolls = 0;
        state = "menu";
    }

    public void gameStart() {
    
        // Check if game can start
        if (state != "menu" || playerManager.getPlayers().size() < 2) {
            throw new IllegalArgumentException("Game cannot start.");
        }
    
        // Set state to game
        state = "game";
        System.out.println("Debug: Game started.");
    }

    public void roundDie() {
        dieManager.diceThrow();
        currentPlayerRolls = dieManager.diceValue();
        System.out.println("Debug: Player rolled dice.");
    }

    public void roundMove() {

        Player currentPlayer = playerManager.getPlayers().get(currentPlayerIndex);
        currentPlayerRolls --;

        // Execute normal move
        if (currentPlayerRolls > 0) {
            currentPlayer.executeMove();

        // Execute action move
        } else {
            currentPlayer.executeTile(tileManager, currentPlayerIndex);
        }

        // Check if player has won
        if (currentPlayer.getPosition() >= tileManager.getTiles().size()) {
            gameEnd(currentPlayer);
        }

        System.out.println("Debug: roundMove();");

    }

    public void roundEnd() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= playerManager.getPlayers().size()) currentPlayerIndex = 0;
        System.out.println("Debug: roundEnd();");
    }

    public void gameEnd(Player player) {
        state = "end";
        playerWinner = player.getName();
        System.out.println("Debug: Game ended.");
    }


}
