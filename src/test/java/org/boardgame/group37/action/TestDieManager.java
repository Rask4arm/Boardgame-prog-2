package org.boardgame.group37.action;
import org.boardgame.group37.backend.die.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDieManager {

    private DieManager dieManager;

    @BeforeEach
    public void setUp() {
        dieManager = new DieManager();
    }

    @Test 
    void testAddRemoveDie() {
        assertEquals(dieManager.diceCount(), 0);
        dieManager.dieAdd();
        assertEquals(dieManager.diceCount(), 1);
        dieManager.dieRemove();
        assertEquals(dieManager.diceCount(), 0);
    }

    @Test
    void testUseDie() {
        dieManager.dieAdd();
        dieManager.dieAdd();
        dieManager.diceThrow();
        assertEquals(dieManager.diceValue() >= 2 && dieManager.diceValue() <= 12, true);
    }

}
