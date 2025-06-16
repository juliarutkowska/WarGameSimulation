package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class General implements Serializable {

    private final String name;
    private int gold;
    private final List<AbstractSoldier> army;
    private transient Secretary secretary; // nie serializujemy sekretarza

    private static final String[] IMIONA = {
            "Marek", "Zofia", "Janek", "Ewa", "Krzysztof", "Ola",
            "Bartek", "Ania", "Tomek", "Kasia", "Piotr", "Julia",
            "Paweł", "Basia", "Michał", "Agnieszka", "Dawid", "Marta",
            "Łukasz", "Ela"
    };

    public General(String name, int startingGold) {
        this.name = name;
        this.gold = startingGold;
        this.army = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public List<AbstractSoldier> getArmy() {
        return army;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }

    private static String losoweImie() {
        Random rand = new Random();
        return IMIONA[rand.nextInt(IMIONA.length)];
    }

    // Kup zwykłego żołnierza z losowym imieniem
    public boolean buySoldier(Rank rank) {
        return buySoldier(rank, losoweImie(), false);
    }

    // Kup zwykłego żołnierza z podanym imieniem
    public boolean buySoldier(Rank rank, String name) {
        return buySoldier(rank, name, false);
    }


    // Kup elitarnego żołnierza z losowym imieniem
    public boolean buyEliteSoldier(Rank rank) {
        return buySoldier(rank, losoweImie(), true);
    }

    // Ogólna metoda do kupowania żołnierzy
    public boolean buySoldier(Rank rank, String name, boolean elite) {
        int cost = 10 * rank.getValue();
        if (gold >= cost) {
            AbstractSoldier s = elite ?
                    new EliteSoldier(rank, name) :
                    new Soldier(rank, name);
            army.add(s);
            gold -= cost;
            if (secretary != null)
                secretary.log(this.name + " kupił żołnierza " + s);
            return true;
        }
        return false;
    }

    public void removeDeadSoldiers() {
        army.removeIf(AbstractSoldier::isDead);
    }

    public int getArmyStrength() {
        return army.stream().mapToInt(AbstractSoldier::getStrength).sum();
    }

    public void gainGold(int amount) {
        this.gold += amount;
    }

    public void loseGold(int amount) {
        this.gold = Math.max(0, this.gold - amount);
    }

    public boolean conductManeuvers(List<? extends AbstractSoldier> participants)
    {
        int totalCost = participants.stream().mapToInt(s -> s.getRank().getValue()).sum();
        if (gold < totalCost) return false;

        for (AbstractSoldier s : participants) {
            int oldExp = s.getExperience();
            Rank oldRank = s.getRank();
            s.gainExperience();
            if (secretary != null) {
                if (s.getRank() != oldRank) {
                    secretary.log(name + ": żołnierz " + s.getId() + " " + s.getName()
                            + " awansował z " + oldRank + " na " + s.getRank());
                } else {
                    secretary.log(name + ": żołnierz " + s.getId() + " " + s.getName()
                            + " zwiększył doświadczenie z " + oldExp + " do " + s.getExperience());
                }
            }
        }

        gold -= totalCost;

        if (secretary != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Generał ").append(name).append(" zakończył manewry: ");
            for (AbstractSoldier s : participants) {
                sb.append("żołnierz ").append(s.getId())
                        .append(" ").append(s.getName())
                        .append(" [").append(s.getRank())
                        .append("] exp: ").append(s.getExperience()).append(", ");
            }
            secretary.log(sb.toString().replaceAll(", $", ""));
        }

        return true;
    }

    public void shootRandomSoldier() {
        if (!army.isEmpty()) {
            Collections.shuffle(army);
            AbstractSoldier removed = army.remove(0);
            if (secretary != null) {
                secretary.log(name + " rozstrzelał żołnierza " + removed.getId() + " " + removed.getName());
            }
        }
    }

    public boolean buySniperSoldier(Rank rank) {
        return buySniperSoldier(rank, losoweImie());
    }

    public boolean buySniperSoldier(Rank rank, String name) {
        int cost = 10 * rank.getValue();
        if (gold >= cost) {
            AbstractSoldier s = new SniperSoldier(rank, name);
            army.add(s);
            gold -= cost;
            if (secretary != null)
                secretary.log(this.name + " kupił żołnierza " + s);
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Generał " + name + ", złoto: " + gold + ", liczba żołnierzy: " + army.size() +
                ", siła: " + getArmyStrength();
    }
}
