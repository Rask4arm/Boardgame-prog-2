package org.boardgame.group37.model.game;

import org.boardgame.group37.model.die.DieManager;
import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerManager;
import org.boardgame.group37.model.tile.BOARDTYPES;
import org.boardgame.group37.model.tile.TileManager;
import org.boardgame.group37.model.tile.action.Action;
import org.boardgame.group37.model.tile.action.ActionMonopolyTile;
import org.boardgame.group37.model.tile.action.ActionSwitch;

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
    private BOARDTYPES boardType;

    /*
    *   STATE = "menu" --> Menu screen
    *   STATE = "game" --> Game is running
    *   STATE = "end" --> Game has ended
    */

    /**
     * Constructor for GameManager class.
     * Initializes the game with default values.
     */
    public GameManager(int width, int size, BOARDTYPES boardType) throws Exception {
        System.out.println("\nDebug: GameManager created.");
        gameReset(true, true, true, width, size, boardType);
        this.boardType = boardType;
    }

    public GameManager(TileManager tileManager, PlayerManager playerManager, BOARDTYPES boardType) {
        System.out.println("\nDebug: GameManager created.");
        gameReset(true, true, true, tileManager, playerManager, boardType);
        this.boardType = boardType;
    }


    /**
     * gameReset method resets the game to default values.
     * @param resetPlayers: Reset players
     * @param resetTiles: Reset tiles
     * @param resetDie: Reset die
     */

    public void gameReset(boolean resetPlayers, boolean resetTiles, boolean resetDie, int width, int size, BOARDTYPES boardType) throws Exception {
        if (resetTiles) tileManager = new TileManager(width, size, boardType);
        if (resetPlayers) playerManager = new PlayerManager();
        if (resetDie) dieManager = new DieManager();
        this.boardType = boardType;
        currentPlayerIndex = 0;
        currentPlayerRolls = 0;
        playerWinner = null;
        state = "menu";
    }

    public void gameReset(boolean resetPlayers, boolean resetTiles, boolean resetDie, TileManager tileManager, PlayerManager playerManager, BOARDTYPES boardType) {
        if (resetTiles) this.tileManager = tileManager;
        if (resetPlayers) this.playerManager = playerManager;
        if (resetDie) dieManager = new DieManager();
        this.boardType = boardType;
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
        
        // Set player manager to actions needed in trasient variables
        for (int i = 0; i < tileManager.getTiles().size(); i++) {
            Action action = tileManager.getTiles().get(i).getAction();
            switch (action.getClass().getSimpleName()) {
                case "ActionMonopolyTile" -> ((ActionMonopolyTile) action).setPlayerManager(playerManager);
                case "ActionSwitch" -> ((ActionSwitch) action).setPlayerManager(playerManager);
            }
        }

        // Check if game can start
        if (state != "menu" || playerManager.getPlayers().size() < 2 || dieManager.diceCount() < 1) {
            throw new IllegalArgumentException("Game cannot start.");
        }
    
        // Set state to game
        state = "game";
        System.out.println("\nDebug: Game started.");

        // Set correct player index
        for (int i = 0; i < playerManager.getPlayers().size(); i++) {
            playerManager.getPlayers().get(i).setIndex(i);
        }
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
    public void roundMove() throws Exception{

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
            currentPlayer.executeTile(tileManager, currentPlayer.getPosition()+1);
            System.out.println(String.format(
                "Debug: roundMove() --> executeTile(); --> playerRolls %d; playerPos %d", 
                currentPlayerRolls, currentPlayer.getPosition())
            );
        }

        // Monopoly check if player passed start tile
        if (boardType == BOARDTYPES.MONOPOLY) {
            if (currentPlayer.getPosition() > tileManager.getTiles().size()) {
                currentPlayer.setPosition(currentPlayer.getPosition() - tileManager.getTiles().size());
                currentPlayer.setMoney(currentPlayer.getMoney() + 200); // Passing start bonus
                System.out.println(String.format(
                    "Debug: Player %d passed start tile and received 200.", 
                    currentPlayerIndex
                ));
            }
        }

        // Check if player has won
        switch(boardType) {
            case BOARDTYPES.SNAKE_AND_LADDERS -> {
                if (currentPlayer.getPosition() >= tileManager.getTiles().size()) {
                    gameEnd(currentPlayer);
                    System.out.println(String.format(
                        "Debug: roundMove() --> gameEnd(player %d) has won.", 
                        currentPlayerIndex)
                    );
                }
            }
            case BOARDTYPES.MONOPOLY -> {
                if (currentPlayer.getMoney() <= 0) {

                    Player richestPlayer = playerManager.getPlayers().stream()
                        .max((p1, p2) -> Integer.compare(p1.getMoney(), p2.getMoney()))
                        .orElse(null);

                    if (richestPlayer == null) {
                        throw new Exception("No winning players found.");
                    }

                    gameEnd(richestPlayer);
                    System.out.println(
                        "Debug: roundMove() --> gameEnd " + richestPlayer.getName() + " has won."
                    );
                }
            }
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
     * setTileManager method sets the TileManager object.
     * @param tileManager: TileManager object
     */
    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
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

