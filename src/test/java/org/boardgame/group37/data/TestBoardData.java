package org.boardgame.group37.data;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestBoardData {

    @Test
    public void testBoardData() {
        BoardData boardData = new BoardData();
        assertEquals(boardData.getBoard().length, 40);
        assertEquals(boardData.getBoard()[0].getTileType(), "Start");
        assertEquals(boardData.getBoard()[0].getTileValue(), 0);
        assertEquals(boardData.getBoard()[0].getTileAction().getClass().getName(), "org.boardgame.group37.backend.tile.action.ActionDefault");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionValue(), 0);
        assertEquals(boardData.getBoard()[0].getTileAction().getActionType(), "Default");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionDescription(), "Move to the next tile");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionDescription(), "Move to the next tile");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionDescription(), "Move to the next tile");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionDescription(), "Move to the next tile");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionDescription(), "Move to the next tile");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionDescription(), "Move to the next tile");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionDescription(), "Move to the next tile");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionDescription(), "Move to the next tile");
        assertEquals(boardData.getBoard()[0].getTileAction().getActionDescription(), "Move to the next tile");
    }
}
