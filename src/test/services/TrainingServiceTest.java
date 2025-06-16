package test.services;

import models.General;
import models.Rank;
import models.Secretary;
import org.junit.jupiter.api.Test;
import services.TrainingService;

import static org.junit.jupiter.api.Assertions.*;

public class TrainingServiceTest {

    @Test
    public void testTrainingCostsGoldAndImprovesExperience() {
        Secretary sec = new Secretary();
        General g = new General("Trener", 100);
        g.setSecretary(sec);
        g.buySoldier(Rank.SZEREGOWY, "Bartek");

        int beforeGold = g.getGold();
        int beforeExp = g.getArmy().get(0).getExperience();

        new TrainingService(sec).train(g, g.getArmy());

        assertTrue(g.getGold() < beforeGold);
        assertTrue(g.getArmy().get(0).getExperience() > beforeExp);
    }

    @Test
    public void testTrainingFailsWhenNotEnoughGold() {
        Secretary sec = new Secretary();
        General g = new General("Biedny", 10);
        g.setSecretary(sec);
        g.buySoldier(Rank.KAPRAL, "Dawid");

        g.loseGold(10); // brak środków

        new TrainingService(sec).train(g, g.getArmy());

        assertEquals(1, g.getArmy().get(0).getExperience()); // nie wzrosło
    }
}
