package test.models;

import models.General;
import models.Rank;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralTest {

    @Test
    public void testBuySoldierWithSufficientGold() {
        General g = new General("Test", 100);
        boolean success = g.buySoldier(Rank.KAPRAL, "Zofia");
        assertTrue(success);
        assertEquals(1, g.getArmy().size());
        assertTrue(g.getGold() < 100);
    }

    @Test
    public void testBuySoldierWithTooLittleGold() {
        General g = new General("Biedak", 5);
        boolean success = g.buySoldier(Rank.KAPRAL, "Tomek");
        assertFalse(success);
        assertEquals(0, g.getArmy().size());
    }

    @Test
    public void testRemoveDeadSoldier() {
        General g = new General("Dowódca", 100);
        g.buySoldier(Rank.SZEREGOWY, "Ola");
        g.getArmy().get(0).loseExperience(); // do 0
        g.removeDeadSoldiers();
        assertEquals(0, g.getArmy().size());
    }
}
