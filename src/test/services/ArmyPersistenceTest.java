package test.services;

import models.General;
import models.Rank;
import org.junit.jupiter.api.Test;
import services.ArmyPersistence;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyPersistenceTest {

    @Test
    public void testSaveAndLoadGeneral() {
        General g = new General("Plikowy", 150);
        g.buySoldier(Rank.MAJOR, "Ela");

        ArmyPersistence.saveToFile(g, "test_save.dat");
        General loaded = ArmyPersistence.loadFromFile("test_save.dat");

        assertNotNull(loaded);
        assertEquals(g.getName(), loaded.getName());
        assertEquals(g.getArmy().size(), loaded.getArmy().size());
    }
}
