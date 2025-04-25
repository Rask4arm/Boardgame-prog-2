package org.boardgame.group37.model.game;

import org.boardgame.group37.model.die.DieManager;
import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerManager;
import org.boardgame.group37.model.tile.TileManager;

/**
 * GameManager class is responsible for managing the game.
 * It controls the game state, player turns, and game end conditions.
 */
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

    /** 
     * Constructor for GameManager class.
     * Initializes the game with default values.
     */
    public GameManager() {
        System.out.println("\nDebug: GameManager created.");
        gameReset(true, true, true);
    }

    /**
     * gameReset method resets the game to default values.
     * @param resetPlayers: Reset players
     * @param resetTiles: Reset tiles
     * @param resetDie: Reset die
     */
    public void gameReset(boolean resetPlayers, boolean resetTiles, boolean resetDie) {
        if (resetTiles) tileManager = new TileManager(10, 50);
        if (resetPlayers) playerManager = new PlayerManager();
        if (resetDie) dieManager = new DieManager();
        currentPlayerIndex = 0;
        currentPlayerRolls = 0;
        playerWinner = null;
        state = "menu";
    }

    /**
     * printProperties method prints the GameManager properties to the console.
     * Used for debugging.
     */
    public void printProperties() {
        System.out.println("Debug: GameManager properties:");
        System.out.println("TileManager: " + tileManager);
        System.out.println("PlayerManager: " + playerManager);
        System.out.println("DieManager: " + dieManager);
        System.out.println("CurrentPlayerIndex: " + currentPlayerIndex);
        System.out.println("CurrentPlayerRolls: " + currentPlayerRolls);
        System.out.println("PlayerWinner: " + playerWinner);
        System.out.println("State: " + state);
    }

    /**
     * gameStart method starts the game.
     * Throws an exception if the game cannot start.
     */
    public void gameStart() {
    
        // Check if game can start
        if (state != "menu" || playerManager.getPlayers().size() < 2 || dieManager.diceCount() < 1) {
            throw new IllegalArgumentException("Game cannot start.");
        }
    
        // Set state to game
        state = "game";
        System.out.println("\nDebug: Game started.");
    }

    /**
     * Throws all die.
     */
    public void roundDie() {
        dieManager.diceThrow();
        currentPlayerRolls = dieManager.diceValue();
        System.out.println(String.format("Debug: Player %d rolled dice.", currentPlayerIndex));
    }

    /**
     * Moves the current player once.
     */
    public void roundMove() {

        Player currentPlayer = playerManager.getPlayers().get(currentPlayerIndex); // 
        currentPlayerRolls --;

        // Execute normal move if player has rolls left
        if (currentPlayerRolls > 0) {
            currentPlayer.executeMove();
            System.out.println(String.format(
                "Debug: roundMove() --> executeMove(); --> playerRolls %d; playerPos %d", 
                currentPlayerRolls, currentPlayer.getPosition())
            );

        // Execute action move if player has no rolls left
        } else {
            currentPlayer.executeTile(tileManager, currentPlayerIndex);
            System.out.println(String.format(
                "Debug: roundMove() --> executeTile(); --> playerRolls %d; playerPos %d", 
                currentPlayerRolls, currentPlayer.getPosition())
            );
        }

        // Check if player has won
        if (currentPlayer.getPosition() >= tileManager.getTiles().size()) {
            gameEnd(currentPlayer);
            System.out.println(String.format(
                "Debug: roundMove() --> gameEnd(player %d) has won.", 
                currentPlayerIndex)
            );
        }

        

    }

    /**
     * Ends the current player's turn.
     */
    public void roundEnd() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= playerManager.getPlayers().size()) currentPlayerIndex = 0;
        System.out.println("Debug: roundEnd();");
    }

    /**
     * Ends the game.
     * @param player: Player that won the game
     */
    private void gameEnd(Player player) {
        state = "end";
        playerWinner = player;
        System.out.println("Debug: Game ended.");
    }

    // Getters

    /**
     * getTileManager method returns the TileManager object.
     * @return TileManager object
     */
    public TileManager getTileManager() {
        return tileManager;
    }

    /**
     * getPlayerManager method returns the PlayerManager object.
     * @return PlayerManager object
     */
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    /**
     * getDieManager method returns the DieManager object.
     * @return DieManager object
     */
    public DieManager getDieManager() {
        return dieManager;
    }

    /**
     * getCurrentPlayerIndex method returns the current player index.
     * @return current player index
     */
    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    /**
     * getCurrentPlayerRolls method returns the current player rolls.
     * @return current player rolls
     */
    public int getCurrentPlayerRolls() {
        return currentPlayerRolls;
    }

    /**
     * getPlayerWinner method returns the player that won the game.
     * @return player that won the game
     */
    public Player getPlayerWinner() {
        return playerWinner;
    }

    /**
     * getState method returns the game state.
     * @return game state
     */
    public String getState() {
        return state;
    }

    public int getCurrentPlayerPosition() {
        return playerManager.getPlayers().get(currentPlayerIndex).getPosition();
    }
}

