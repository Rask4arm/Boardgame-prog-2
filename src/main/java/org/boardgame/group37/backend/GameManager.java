package org.boardgame.group37.backend;

// import org.boardgame.group37.backend.*; // For remembering to import all classes when folders are used in the future
// import java.util.ArrayList; // not used yet but will probably be used in the future.

public class GameManager {
    private TileManager tileManager;
    private PlayerManager playerManager;
    private DieManager dieManager;
    private int currentPlayerIndex;
    private int currentPlayerRolls;
    private Player playerWinner;
    private String state;

    /*
    *   STATE = "menu" --> Menu screen
    *   STATE = "game" --> Game is running
    *   STATE = "end" --> Game has ended
    */

    public GameManager() {
        System.out.println("\nDebug: GameManager created.");
        gameReset(true, true, true);
    }

    public void gameReset(boolean resetPlayers, boolean resetTiles, boolean resetDie) {
        if (resetTiles) tileManager = new TileManager(10, 50);
        if (resetPlayers) playerManager = new PlayerManager();
        if (resetDie) dieManager = new DieManager();
        currentPlayerIndex = 0;
        currentPlayerRolls = 0;
        playerWinner = null;
        state = "menu";
    }

    public void gameStart() {
    
        // Check if game can start
        if (state != "menu" || playerManager.getPlayers().size() < 2 || dieManager.diceCount() < 1) {
            throw new IllegalArgumentException("Game cannot start.");
        }
    
        // Set state to game
        state = "game";
        System.out.println("\nDebug: Game started.");
    }

    public void roundDie() {
        dieManager.diceThrow();
        currentPlayerRolls = dieManager.diceValue();
        System.out.println(String.format("Debug: Player %d rolled dice.", currentPlayerIndex));
    }

    public void roundMove() {

        Player currentPlayer = playerManager.getPlayers().get(currentPlayerIndex);
        currentPlayerRolls --;

        // Execute normal move
        if (currentPlayerRolls > 0) {
            currentPlayer.executeMove();
            System.out.println(String.format("Debug: roundMove() --> executeMove(); --> playerRolls %d; playerPos %d", currentPlayerRolls, currentPlayer.getPosition()));

        // Execute action move
        } else {
            currentPlayer.executeTile(tileManager, currentPlayerIndex);
            System.out.println(String.format("Debug: roundMove() --> executeTile(); --> playerRolls %d; playerPos %d", currentPlayerRolls, currentPlayer.getPosition()));
        }

        // Check if player has won
        if (currentPlayer.getPosition() >= tileManager.getTiles().size()) {
            gameEnd(currentPlayer);
            System.out.println(String.format("Debug: roundMove() --> gameEnd(player %d) has won.", currentPlayerIndex));
        }

        

    }

    public void roundEnd() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= playerManager.getPlayers().size()) currentPlayerIndex = 0;
        System.out.println("Debug: roundEnd();");
    }

    private void gameEnd(Player player) {
        state = "end";
        playerWinner = player;
        System.out.println("Debug: Game ended.");
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public DieManager getDieManager() {
        return dieManager;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public int getCurrentPlayerRolls() {
        return currentPlayerRolls;
    }

    public Player getPlayerWinner() {
        return playerWinner;
    }

    public String getState() {
        return state;
    }
}

