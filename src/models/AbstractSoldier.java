package models;

import java.io.Serializable;

// Klasa bazowa dla każdego typu żołnierza
public abstract class AbstractSoldier implements Serializable {
    private static int idCounter = 0;
    protected final int id;
    protected final String name;
    protected int experience;

    public AbstractSoldier(String name) {
        this.id = ++idCounter;
        this.name = name;
        this.experience = 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public abstract int getStrength();
    public abstract Rank getRank();
    public abstract void gainExperience();
    public abstract void loseExperience();
    public abstract boolean isDead();

    @Override
    public abstract String toString();
}
