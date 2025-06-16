package test.models;

import models.Rank;
import models.SniperSoldier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SniperSoldierTest {

    @Test
    void testStrengthCalculation() {
        SniperSoldier s = new SniperSoldier(Rank.KAPRAL, "Dawid");
        assertEquals(2 * 2 * 1, s.getStrength()); // 2 * stopień * doświadczenie
    }

    @Test
    void testGainExperienceEveryTwoTurns() {
        SniperSoldier s = new SniperSoldier(Rank.KAPRAL, "Dawid");
        s.gainExperience(); // 1st time → no change
        assertEquals(1, s.getExperience());
        s.gainExperience(); // 2nd time → +1
        assertEquals(2, s.getExperience());
    }

    @Test
    void testNoPromotion() {
        SniperSoldier s = new SniperSoldier(Rank.KAPRAL, "Dawid");
        for (int i = 0; i < 10; i++) s.gainExperience(); // plenty
        assertEquals(Rank.KAPRAL, s.getRank()); // No promotion
    }

    @Test
    void testDeath() {
        SniperSoldier s = new SniperSoldier(Rank.KAPRAL, "Dawid");
        s.loseExperience();
        assertFalse(s.isDead());
        s.loseExperience();
        assertTrue(s.isDead());
    }
}
