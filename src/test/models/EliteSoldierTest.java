package test.models;

import models.EliteSoldier;
import models.Rank;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EliteSoldierTest {

    @Test
    void testInitialValues() {
        EliteSoldier s = new EliteSoldier(Rank.KAPRAL, "Zenek");
        assertEquals(1, s.getExperience());
        assertEquals(Rank.KAPRAL, s.getRank());
    }

    @Test
    void testGainExperienceAndPromotion() {
        EliteSoldier s = new EliteSoldier(Rank.KAPRAL, "Zenek");
        for (int i = 0; i < 5; i++) s.gainExperience();
        assertEquals(Rank.KAPITAN, s.getRank());
        assertEquals(1, s.getExperience());
    }

    @Test
    void testLoseExperienceAndDeath() {
        EliteSoldier s = new EliteSoldier(Rank.KAPRAL, "Zenek");
        s.loseExperience();
        s.loseExperience();
        assertTrue(s.isDead());
    }

    @Test
    void testStrengthCalculation() {
        EliteSoldier s = new EliteSoldier(Rank.KAPRAL, "Zenek");
        s.gainExperience(); // +2
        assertEquals((int)(1.5 * 2 * 3), s.getStrength()); // 2*3=6, *1.5=9
    }
}