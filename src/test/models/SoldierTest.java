package test.models;

import models.Rank;
import models.Soldier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoldierTest {

    @Test
    public void testInitialStrength() {
        Soldier s = new Soldier(Rank.KAPRAL, "Marek");
        assertEquals(2, s.getStrength());
    }

    @Test
    public void testExperienceGainAndPromotion() {
        Soldier s = new Soldier(Rank.SZEREGOWY, "Janek");
        for (int i = 0; i < 5; i++) {
            s.gainExperience();
        }
        assertEquals(Rank.KAPRAL, s.getRank());
        assertEquals(1, s.getExperience());
    }

    @Test
    public void testDeathCondition() {
        Soldier s = new Soldier(Rank.SZEREGOWY, "Ewa");
        s.loseExperience();
        assertTrue(s.isDead());
    }
}
