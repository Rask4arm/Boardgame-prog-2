package org.boardgame.group37.backend;

import java.util.ArrayList;

public class GameManager {
    private TileManager tileManager;
    private PlayerManager playerManager;
    private DieManager dieManager;
    private int currentPlayerIndex;
    private int currentPlayerRolls;
    private String state;

    public GameManager() {
        tileManager = new TileManager(10, 10);
        playerManager = new PlayerManager();
        dieManager = new DieManager();
        currentPlayerIndex = 0;
        currentPlayerRolls = 0;
        state = "menu";
    }

    public void gameStart() {

        // Check if game can start
        if (state != "menu" || playerManager.getPlayers().size() < 2) {
            System.err.println("Error: Game cannot start.");
            return;
        }

        // Set state to game
        state = "game";
        System.out.println("Debug: Game started.");
    }


}
