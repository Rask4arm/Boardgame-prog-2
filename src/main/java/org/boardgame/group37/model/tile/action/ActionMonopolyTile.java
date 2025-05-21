package org.boardgame.group37.model.tile.action;

import javafx.scene.paint.Color;
import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerManager;

public class ActionMonopolyTile implements Action {

    private int value;
    private int rent;
    private Integer playerIndexOwner = null;
    private transient PlayerManager playerManager; // transient to avoid serialization issues with saving/loading json.

    public ActionMonopolyTile(int value) {
        this.value = value;
        this.rent = value / 2;
    }

    /**
     * getColor method returns the color of the action.
     *
     * @return color of the action
     */
    @Override
    public Color getColor() {
        return (playerIndexOwner != null) ? playerManager.getPlayers().get(playerIndexOwner).getColor() : Color.LIGHTGRAY;
    }

    public void purchaseTile(Player player) {
        if (player.getMoney() >= value) {
            player.setMoney(player.getMoney() - value);
            playerIndexOwner = player.getIndex();
            System.out.println("Debug: Player " + player.getName() + " purchased the tile for " + value);
        } else {
            System.out.println("Debug: Player " + player.getName() + " does not have enough money to purchase the tile.");
        }
    }

    /**
     * execute method moves the player one step forward.
     * @param player: Player object
     */
    @Override
    public void execute(Player player) {
        if (player.getIndex() == playerIndexOwner) {
            System.out.println("Debug: Player " + player.getName() + " is already the owner of the tile.");
            return;
        }
        if (playerIndexOwner != null) {
            player.setMoney(player.getMoney() - rent);
            playerManager.getPlayers().get(playerIndexOwner).setMoney(playerManager.getPlayers().get(playerIndexOwner).getMoney() + rent);
            System.out.println("Debug: Player " + player.getName() + " paid rent of " + rent + " to player " + playerManager.getPlayers().get(playerIndexOwner).getName());
        } else if (player.getMoney() >= value) {
            purchaseTile(player);
            System.out.println("Debug: Player " + player.getName() + " purchased the tile for " + value);
        } else {
            System.out.println("Debug: Player " + player.getName() + " does not have enough money to purchase the tile.");
        }
    };

    /**
     * Gives this object the PlayerManager.
     * @param playerManager the PlayerManager to be set
     */
    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Gets the index of the current owner of the tile. (player)
     * @return the index of the owner
     */
    public Integer getPlayerIndexOwner() {
        return playerIndexOwner;
    }

    /**
     * toString method returns the name of the action.
     * @return name of the action
     */
    @Override
    public String toString() {
        return "ActionMonopolyTile";
    }
}
