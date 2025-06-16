package models;

public class SniperSoldier extends AbstractSoldier {

    private final Rank rank;
    private int maneuversSinceLastGain = 0;

    public SniperSoldier(Rank rank, String name) {
        super(name);
        this.rank = rank;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public int getStrength() {
        return 2 * rank.getValue() * experience; // silny, ale powolny
    }

    @Override
    public void gainExperience() {
        maneuversSinceLastGain++;
        if (maneuversSinceLastGain >= 2) {
            experience++;
            maneuversSinceLastGain = 0;
        }
    }

    @Override
    public void loseExperience() {
        experience--;
    }

    @Override
    public boolean isDead() {
        return experience <= 0;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " - SNAJPER " + rank + " (exp: " + experience + ")";
    }
}
