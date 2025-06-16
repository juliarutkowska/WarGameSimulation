package services;

import models.AbstractSoldier;
import models.General;
import models.Secretary;
import models.SniperSoldier;

import java.util.List;

public class BattleService {

    private final Secretary secretary;

    public BattleService(Secretary secretary) {
        this.secretary = secretary;
    }

    public void fight(General g1, General g2) {
        int strength1 = g1.getArmyStrength();
        int strength2 = g2.getArmyStrength();

        secretary.log("Bitwa między " + g1.getName() + " (siła " + strength1 + ") a " + g2.getName() + " (siła " + strength2 + ")");

        if (strength1 > strength2) {
            resolveBattle(g1, g2);
        } else if (strength2 > strength1) {
            resolveBattle(g2, g1);
        } else {
            secretary.log("Remis — obaj generałowie muszą rozstrzelać jednego żołnierza.");
            g1.shootRandomSoldier();
            g2.shootRandomSoldier();
        }

        g1.removeDeadSoldiers();
        g2.removeDeadSoldiers();
    }

    private void resolveBattle(General winner, General loser) {
        secretary.log("Zwyciężył " + winner.getName());

        int loot = (int) (0.1 * loser.getGold());
        loser.loseGold(loot);
        winner.gainGold(loot);
        secretary.log("Łup: " + loot + " monet");

        for (AbstractSoldier s : loser.getArmy()) {
            int before = s.getExperience();
            s.loseExperience();
            if (!s.isDead()) {
                secretary.log(formatLog(loser.getName(), s, before, s.getExperience(), false));
            }
        }

        for (AbstractSoldier s : winner.getArmy()) {
            int before = s.getExperience();
            s.gainExperience();
            secretary.log(formatLog(winner.getName(), s, before, s.getExperience(), true));
        }
    }

    private String formatLog(String generalName, AbstractSoldier s, int before, int after, boolean gained) {
        String typeTag = s instanceof SniperSoldier ? "[SNAJPER] " : "";
        String changeType = gained ? "zyskał" : "stracił";
        return typeTag + generalName + ": żołnierz " + s.getId() + " " + s.getName() +
                " (" + s.getClass().getSimpleName() + ") " + changeType +
                " doświadczenie z " + before + " do " + after;
    }
}
