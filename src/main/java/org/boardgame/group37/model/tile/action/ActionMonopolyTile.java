package org.boardgame.group37.model.tile.action;

import javafx.scene.paint.Color;
import org.boardgame.group37.model.player.Player;
import org.boardgame.group37.model.player.PlayerManager;

public class ActionMonopolyTile implements Action {

    private int value;
    private int rent;
    private Integer playerIndexOwner = null;
    private transient PlayerManager playerManager; // transient to avoid serialization issues with saving/loading json.

    public ActionMonopolyTile(int value, Integer playerIndexOwner) {
        this.value = value;
        this.rent = value / 2;
        this.playerIndexOwner = playerIndexOwner;
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
            playerIndexOwner = 1;
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

    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }
}
