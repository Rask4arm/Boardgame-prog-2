package org.boardgame.group37;
import org.boardgame.group37.backend.die.Die;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDie {

    @Test
    public void testRoll() {
        Die die = new Die();
        die.executeThrow();
        int dieValue = die.getValue();
        assertEquals(dieValue >= 1 && dieValue <= 6, true);
    }

    @Test
    public void testGetFaceValue() {
        Die die = new Die();
        die.executeThrow();
        int faceValue = die.getValue();
        assertEquals(faceValue >= 1 && faceValue <= 6, true);
    }

}
