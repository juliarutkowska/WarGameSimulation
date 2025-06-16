package models;

// Zwykły żołnierz (implementacja domyślna)
public class Soldier extends AbstractSoldier {
    private Rank rank;

    public Soldier(Rank rank, String name) {
        super(name);
        this.rank = rank;
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public int getStrength() {
        return rank.getValue() * experience;
    }

    @Override
    public void gainExperience() {
        experience++;
        if (experience >= 5 * rank.getValue() && rank != Rank.MAJOR) {
            rank = rank.next();
            experience = 1;
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
        return "[" + id + "] " + name + " - " + rank + " (exp: " + experience + ")";
    }
}
