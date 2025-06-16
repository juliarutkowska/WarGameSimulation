package models;

// Żołnierz elitarny – mocniejszy i szybciej zdobywa doświadczenie
public class EliteSoldier extends AbstractSoldier {
    private Rank rank;

    public EliteSoldier(Rank rank, String name) {
        super(name);
        this.rank = rank;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public int getStrength() {
        return (int)(1.5 * rank.getValue() * experience);
    }

    @Override
    public void gainExperience() {
        experience += 2;
        if (experience >= 5 * rank.getValue() && rank != Rank.MAJOR) {
            rank = rank.next();
            experience = 1;
        }
    }

    @Override
    public void loseExperience() {
        experience -= 2;
    }

    @Override
    public boolean isDead() {
        return experience <= 0;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " - ELITE " + rank + " (exp: " + experience + ")";
    }
}
