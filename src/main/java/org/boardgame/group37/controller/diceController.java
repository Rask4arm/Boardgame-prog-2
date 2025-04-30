package org.boardgame.group37.controller;
import org.boardgame.group37.model.game.GameManager;
 
public class diceController {

    public void buttonRollDie(GameManager gameManager) {
        gameManager.getDieManager().diceThrow();

    };
    public void buttonAddDie(GameManager gameManager) {
        gameManager.getDieManager().dieAdd();
    };
    public void buttonRemoveDie(GameManager gameManager) {
        gameManager.getDieManager().dieRemove();
    };
}