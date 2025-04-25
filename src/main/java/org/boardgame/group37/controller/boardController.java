package org.boardgame.group37.controller;
import org.boardgame.group37.model.game.GameManager;
import org.boardgame.group37.model.tile.TileDataManager;

public class boardController {

    public void buttonGenerateLadders(GameManager gameManager) {
        gameManager.getTileManager().tilesGenerate();
    }

    public void buttonLoad(GameManager gameManager, String fileName) {
        try {
            gameManager.getTileManager().setTiles(TileDataManager.dataLoad(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void buttonSave(GameManager gameManager, String fileName) {
        try {
            TileDataManager.dataSave(gameManager.getTileManager().getTiles(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }   
}
