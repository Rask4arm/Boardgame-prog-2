package org.boardgame.group37.controller;
import org.boardgame.group37.model.game.GameManager;
import org.boardgame.group37.model.tile.TileDataManager;

public class boardController {

    /**
     * This method is responsible for generating the tiles of the board.
     * @param gameManager: GameManager
     */
    public void buttonGenerateLadders(GameManager gameManager) {
        gameManager.getTileManager().tilesGenerate();
    }

    /**
     * This method is responsible for loading the tiles of the board from a file.
     * @param gameManager: GameManager
     * @param fileName: String
     */
    public void buttonLoad(GameManager gameManager, String fileName) {
        try {
            gameManager.getTileManager().setTiles(TileDataManager.dataLoad(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method is responsible for saving the tiles of the board to a file.
     * @param gameManager: GameManager
     * @param fileName: String
     */
    public void buttonSave(GameManager gameManager, String fileName) {
        try {
            TileDataManager.dataSave(gameManager.getTileManager().getTiles(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }   
}
