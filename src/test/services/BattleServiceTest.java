package test.services;

import models.*;
import org.junit.jupiter.api.Test;
import services.BattleService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BattleServiceTest {

    @Test
    public void testVictoryTransfersGold() {
        Secretary sec = new Secretary();
        General g1 = new General("Zwycięzca", 100);
        General g2 = new General("Przegrany", 100);

        g1.setSecretary(sec);
        g2.setSecretary(sec);

        g1.buySoldier(Rank.MAJOR);
        g2.buySoldier(Rank.SZEREGOWY);

        new BattleService(sec).fight(g1, g2);

        assertTrue(g1.getGold() > 100 - 40);
        assertTrue(g2.getGold() < 100 - 10);
    }

    @Test
    public void testDrawCausesExecution() {
        Secretary sec = new Secretary();
        General g1 = new General("A", 100);
        General g2 = new General("B", 100);

        g1.setSecretary(sec);
        g2.setSecretary(sec);

        g1.buySoldier(Rank.KAPRAL);
        g2.buySoldier(Rank.KAPRAL);

        new BattleService(sec).fight(g1, g2);

        assertEquals(0, g1.getArmy().size());
        assertEquals(0, g2.getArmy().size());
    }

    @Test
    void testBattleWinnerGetsGold() {
        Secretary secretary = new Secretary();
        General jan = new General("Jan", 100);
        General pawel = new General("Paweł", 100);
        jan.setSecretary(secretary);
        pawel.setSecretary(secretary);

        jan.buyEliteSoldier(Rank.KAPRAL);
        jan.buyEliteSoldier(Rank.KAPRAL);
        pawel.buySoldier(Rank.SZEREGOWY);

        BattleService battleService = new BattleService(secretary);
        battleService.fight(jan, pawel);

        assertTrue(jan.getGold() > 80);
        assertTrue(pawel.getGold() < 100);
    }

    @Test
    void testDrawResultsInExecution() {
        Secretary secretary = new Secretary();
        General a = new General("A", 100);
        General b = new General("B", 100);
        a.setSecretary(secretary);
        b.setSecretary(secretary);

        a.buySoldier(Rank.SZEREGOWY);
        b.buySoldier(Rank.SZEREGOWY);

        BattleService bs = new BattleService(secretary);
        bs.fight(a, b);

        assertTrue(a.getArmy().size() <= 1);
        assertTrue(b.getArmy().size() <= 1);
    }

    @Test
    void testSniperSoldierLogTag() {
        Secretary secretary = new Secretary();
        General jan = new General("Jan", 100);
        General pawel = new General("Paweł", 100);
        jan.setSecretary(secretary);
        pawel.setSecretary(secretary);

        jan.buySniperSoldier(Rank.KAPRAL);
        pawel.buySoldier(Rank.SZEREGOWY);

        BattleService bs = new BattleService(secretary);
        bs.fight(jan, pawel);

        boolean sniperLogged = secretary.getReports().stream()
                .anyMatch(r -> r.getMessage().contains("[SNAJPER]"));

        assertTrue(sniperLogged);
    }
}
